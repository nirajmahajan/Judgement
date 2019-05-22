package android.example.judgement.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.widget.Toast;

@Database(entities = {Player.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract Dao dao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "player-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE.dao().purge();
        INSTANCE = null;
    }

    public static void verifiedAddPlayer(Context ctx, String Name) {
        if(Name.equals("")){
            Toast.makeText(ctx, "Enter a Valid Name", Toast.LENGTH_SHORT).show();
        }
        else {
            AppDatabase db = getAppDatabase(ctx);

            try {
                Player player = db.dao().findByName(Name);
                if (!String.valueOf(player).equals("null")) {
                    Toast.makeText(ctx, "Name '" + Name + "' is Already Taken!", Toast.LENGTH_SHORT).show();
                } else {
                    player.setName(Name);
                    player.setDealer(false);
                    player.setScore(0);
                    db.dao().insertAll(player);
                    Toast.makeText(ctx, "Name Added Succesfully!", Toast.LENGTH_SHORT).show();

                }
            } catch (Exception e){
                Player player = new Player();
                player.setName(Name);
                player.setDealer(false);
                player.setScore(0);
                db.dao().insertAll(player);
                Toast.makeText(ctx, "Name Added Succesfully!", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public static void makeDealer(Context ctx, Player player) {
        AppDatabase db = getAppDatabase(ctx);
        Player prev_dealer = db.dao().findDealer(true);
        player.setDealer(true);
        prev_dealer.setDealer(false);
    }

    public static void swapPlayers(Player player1, Player player2) {
        int id1 = player1.getId();
        player1.setId(player2.getId());
        player2.setId(id1);
    }

    public static void verifiedRename(Context ctx, Player player,String Name) {
        int id = player.getId();
        int score = player.getScore();
        boolean dealer = player.getDealer();

        if(Name.equals("")){
            Toast.makeText(ctx, "Not a Valid Name", Toast.LENGTH_SHORT).show();
        }
        else {
            AppDatabase db = getAppDatabase(ctx);

            try {
                Player player2 = db.dao().findByName(Name);
                if (!String.valueOf(player2).equals("null")) {
                    Toast.makeText(ctx, "Name '" + Name + "' is Already Taken!", Toast.LENGTH_SHORT).show();
                } else {
                    db.dao().delete(player);
                    player2.setName(Name);
                    player2.setDealer(dealer);
                    player2.setScore(score);
                    player2.setId(id);

                    db.dao().insertAll(player2);
                    Toast.makeText(ctx, "Name Renamed Succesfully!", Toast.LENGTH_SHORT).show();

                }
            } catch (Exception e){
                db.dao().delete(player);
                Player player2 = new Player();
                player2.setName(Name);
                player2.setId(id);
                player2.setDealer(dealer);
                player2.setScore(score);
                db.dao().insertAll(player2);
                Toast.makeText(ctx, "Name Renamed Succesfully!", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
