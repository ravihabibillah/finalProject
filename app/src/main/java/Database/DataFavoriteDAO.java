package Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface DataFavoriteDAO {
    @Insert
    Long insert(DataFavorite dataFavorite);

    @Query("Select * from data_db")
    List<DataFavorite> getData();

    @Query("Update data_db set catatan = :catatan where id = :id")
    void update(String catatan, int id);

    @Query("Delete from data_db where id = :id")
    void deletebyId(int id);
}
