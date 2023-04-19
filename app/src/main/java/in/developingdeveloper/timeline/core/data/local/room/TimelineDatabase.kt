package `in`.developingdeveloper.timeline.core.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import `in`.developingdeveloper.timeline.core.data.local.tags.PersistableTag
import `in`.developingdeveloper.timeline.core.data.local.tags.TagDao

@Database(
    entities = [PersistableTag::class],
    version = 1,
)
abstract class TimelineDatabase : RoomDatabase() {

    abstract fun tagDao(): TagDao

    companion object {
        private const val DB_NAME = "timeline-database"

        fun build(context: Context): TimelineDatabase {
            return Room
                .databaseBuilder(
                    context,
                    TimelineDatabase::class.java,
                    DB_NAME,
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
