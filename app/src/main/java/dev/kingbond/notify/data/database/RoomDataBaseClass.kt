package dev.kingbond.notify.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.kingbond.notify.ui.goal.model.GoalModel
import dev.kingbond.notify.ui.task.model.TaskModel

@Database(entities = [GoalModel::class,TaskModel::class],version = 3)
abstract class RoomDataBaseClass :RoomDatabase() {
    abstract fun getDao(): ClassDao
    companion object{
        private var INSTANCE: RoomDataBaseClass?= null

        fun getDataBaseObject(context: Context): RoomDataBaseClass {
            if(INSTANCE == null){
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDataBaseClass::class.java,
                    "db_name"
                )
                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()
                return INSTANCE!!
            }else return  INSTANCE!!
        }

    }

}