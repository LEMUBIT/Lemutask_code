package lemuel.charles.charl.lemutask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by charles on 03/02/2017.
 */

public class sql_helper_lemutask extends SQLiteOpenHelper{
    public static final String DATABASE_NAME= "lemutask.db";

    ///////////////////////////////////////////////////////////Table for Todolist
    public static final String TABLE_NAME="todo";
    public static final String COL1="_id";
    public static final String COL2="TITLE";
    public static final String COL3="DESCRIPTION";
    public static final String COL4="STIME";
    public static final String COL5="ETIME";
    public static final String COL6="ISTIMED";
    public static final String COL7="ISDONE";
    public static final String COL8="DAY";
    public static final String COL9="MONTH";
    public static final String COL10="YEAR";
    public static final String COLdt="TDATE";
////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////
    public static final String CLASSES_TABLE_NAME="classes";
    public static final String COL11="_id";
    public static final String COL12="TITLE";
    public static final String COL13="CODE";
    public static final String COL14="CREDIT";
    public static final String COL15="VENUE";
    public static final String COL16="LECTURERS";
    public static final String COL17="SRTCLASSTIME";
    public static final String COL18="STPCLASSTIME";
    public static final String COL19="ORIGINALTIME";//original time so that time can be sorted out, when selecting classes to list view
    public static final String COL20="DAY_C";
    ///////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////
    public static final String ASSIGNMENT_TABLE_NAME="homework";
    public static final String COL21="_id";
    public static final String COL22="TITLE";
    public static final String COL23="DETAIL";
    public static final String COL24="ISDONE";
    public static final String COL25="DAY";
    public static final String COL26="MONTH";
    public static final String COL27="YEAR";
    public static final String COL28="ISTIMED";
    public static final String COL29="TIME";
    /////////////////////////////////////////////////////


    ////////////////////////////////////////////////////
    public static final String PROJECT_TABLE_NAME="project";
    public static final String COL31="_id";
    public static final String COL32="TITLE";
    public static final String COL33="DETAIL";
    public static final String COL34="TEAM";
    public static final String COL35="DAY";
    public static final String COL36="MONTH";
    public static final String COL37="YEAR";
    public static final String COL38="ROLE";
    public static final String COL39="ASSIGNEDTASK";
    public static final String COL40="PERCENT";
    ///////////////////////////////////////////////////

    //////////////////////////////////////////////////
    public static final String CONTACT_TABLE_NAME="contact";
    public static final String COL41="_id";
    public static final String COL42="NAME";
    public static final String COL43="SURNAME";
    public static final String COL44="NICK";
    public static final String COL45="EMAIL";
    public static final String COL46="PHONE";
    public static final String COL47="FACEBOOK";
    public static final String COL48="TWITTER";
    public static final String COL49="INSTAGRAM";
    public static final String COL50="SKILL";


    ////////////////////////////////////////////////////



    public sql_helper_lemutask(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( _id INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DESCRIPTION TEXT, STIME TEXT, ETIME TEXT, ISTIMED INTEGER, ISDONE INTEGER, DAY INTEGER, MONTH INTEGER, YEAR INTEGER, TDATE DATE)");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+CLASSES_TABLE_NAME+" ( _id INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, CODE TEXT, CREDIT INTEGER, VENUE TEXT, LECTURERS TEXT, SRTCLASSTIME TEXT, STPCLASSTIME TEXT, ORIGINALTIME INTEGER, DAY_C TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ASSIGNMENT_TABLE_NAME+" ( _id INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DETAIL TEXT, ISDONE INTEGER, DAY INTEGER, MONTH INTEGER, YEAR INTEGER, ISTIMED INTEGER, TIME TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+PROJECT_TABLE_NAME+" ( _id INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DETAIL TEXT, TEAM INTEGER, DAY INTEGER, MONTH INTEGER, YEAR INTEGER, ROLE TEXT, ASSIGNEDTASK TEXT, PERCENT INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS "+CONTACT_TABLE_NAME+" ( _id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, NICK TEXT, EMAIL TEXT, PHONE TEXT, FACEBOOK TEXT, TWITTER TEXT, INSTAGRAM, TEXT, SKILL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+CLASSES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ASSIGNMENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+PROJECT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+CONTACT_TABLE_NAME);
        onCreate(db);
    }

    /////////////////contact
    public boolean insertContactdata(String name, String surname, String nick, String email, String phone, String facebook, String twitter, String instagram, String skill)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(COL42,name);
        cv.put(COL43,surname);
        cv.put(COL44,nick);
        cv.put(COL45,email);
        cv.put(COL46,phone);
        cv.put(COL47,facebook);
        cv.put(COL48,twitter);
        cv.put(COL49,instagram);
        cv.put(COL50,skill);

        long result= db.insert(CONTACT_TABLE_NAME,null,cv);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
            return true;
    }

    public Cursor getContact()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+CONTACT_TABLE_NAME,null);

        return res;

    }



    ///////////////////////
    public boolean editproject(String id,String title,String detail,String team,String day,String month,String year,String role,String task)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COL32,title);
        cv.put(COL33,detail);
        cv.put(COL34,team);
        cv.put(COL35,day);
        cv.put(COL36,month);
        cv.put(COL37,year);
        cv.put(COL38,role);
        cv.put(COL39,task);


        db.update(PROJECT_TABLE_NAME,cv, "_id = ?",new String[]{id});
        db.close();
        return true;
    }
///

    public boolean editproject(String id,String title,String detail,String team,String day,String month,String year)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COL32,title);
        cv.put(COL33,detail);
        cv.put(COL34,team);
        cv.put(COL35,day);
        cv.put(COL36,month);
        cv.put(COL37,year);
        cv.put(COL38,"No Team Role");
        cv.put(COL39,"No Team Task");


        db.update(PROJECT_TABLE_NAME,cv, "_id = ?",new String[]{id});
        db.close();
        return true;
    }
    //
    public boolean updateproject(String id,String percentage)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL40,percentage);
        db.update(PROJECT_TABLE_NAME,cv, "_id = ?",new String[]{id});
        db.close();
        return true;
    }
/////

    public Cursor getProject()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+PROJECT_TABLE_NAME,null);

        return res;

    }
    //
    public Cursor getProjectview(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+PROJECT_TABLE_NAME+" WHERE _id="+id,null);

        return res;

    }


    /////
    public boolean insertPRJdata(String title, String detail, String team, String role, String task, String day, String month, String year)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(COL32,title);
        cv.put(COL33,detail);
        cv.put(COL34,team);
        cv.put(COL35,day);
        cv.put(COL36,month);
        cv.put(COL37,year);
        cv.put(COL38,role);
        cv.put(COL39,task);
        cv.put(COL40,"0");

        long result= db.insert(PROJECT_TABLE_NAME,null,cv);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
            return true;
    }
    /////
    public boolean insertPRJdata(String title, String detail, String team, String day, String month, String year)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(COL32,title);
        cv.put(COL33,detail);
        cv.put(COL34,team);
        cv.put(COL35,day);
        cv.put(COL36,month);
        cv.put(COL37,year);
        cv.put(COL38,"No Team Role");
        cv.put(COL39,"No Team Task");
        cv.put(COL40,"0");

        long result= db.insert(PROJECT_TABLE_NAME,null,cv);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
            return true;
    }
    /////
    public boolean insertTODOdata(String title, String description, String stime, String etime, String istimed, String day, String month, String year)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(COL2,title);
        cv.put(COL3,description);
        cv.put(COL4,stime);
        cv.put(COL5,etime);
        cv.put(COL6,istimed);
        cv.put(COL7,"0");
        cv.put(COL8,day);
        cv.put(COL9,month);
        cv.put(COL10,year);

        if(month.length()==1)//if month is 1 digit e.g like 2, make it 02, so instead of 2017-2-11 let it be 2017-02-11
            month="0"+month;

        if(day.length()==1)// same as month above
            day="0"+day;

        String date=year+"-"+month+"-"+day;//make the date in the format year-month-day
        cv.put(COLdt,date);

        long result= db.insert(TABLE_NAME,null,cv);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
            return true;
    }
    //
    public boolean insertAssignmentdata(String title, String detail, String istimed, String time, String day, String month, String year)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(COL22,title);
        cv.put(COL23,detail);
        cv.put(COL24,"0");
        cv.put(COL25,day);
        cv.put(COL26,month);
        cv.put(COL27,year);
        cv.put(COL28,istimed);
        cv.put(COL29,time);


        long result= db.insert(ASSIGNMENT_TABLE_NAME,null,cv);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
            return true;
    }

    /////////the same functions but with different arguments, depending on situation.
    public boolean insertAssignmentdata(String title, String detail, String istimed)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Calendar calender= Calendar.getInstance();
        ContentValues cv=new ContentValues();
        cv.put(COL22,title);
        cv.put(COL23,detail);
        cv.put(COL24,"0");
        cv.put(COL25,String.valueOf(calender.get(Calendar.DAY_OF_MONTH)));
        cv.put(COL26,String.valueOf(calender.get(Calendar.MONTH)));
        cv.put(COL27,String.valueOf(calender.get(Calendar.YEAR)));
        cv.put(COL28,istimed);
        cv.put(COL29,"8");
        //original hour...like org hour in classes table...used to query by order,
        // it is "8"   8.00AM O,clock since its my chosen default for person not choosing time


        long result= db.insert(ASSIGNMENT_TABLE_NAME,null,cv);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
            return true;
    }
    public boolean updatetodo(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL7,"1");
        db.update(TABLE_NAME,cv, "_id = ?",new String[]{id});
        db.close();
        return true;
    }
    public boolean updatetodoundone(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL7,"0");
        db.update(TABLE_NAME,cv, "_id = ?",new String[]{id});
        db.close();
        return true;
    }

    public boolean updateAssignment(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL24,"1");
        db.update(ASSIGNMENT_TABLE_NAME,cv, "_id = ?",new String[]{id});
        db.close();
        return true;
    }

    //
    public boolean updateAssignmentundone(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL24,"0");
        db.update(ASSIGNMENT_TABLE_NAME,cv, "_id = ?",new String[]{id});
        db.close();
        return true;
    }


    //// TODO: 17/02/2017 :get sql statement to get largest ID and increment
    public Cursor getlatestId()
    { SQLiteDatabase db=this.getWritableDatabase();

        // Cursor res=db.rawQuery("SELECT MAX(_id) AS LATEST_ID from "+TABLE_NAME,null);
        Cursor res=db.rawQuery("SELECT * from "+TABLE_NAME+" ORDER BY _id DESC",null);
        return res;

    }


    public Cursor getAssignment()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+ASSIGNMENT_TABLE_NAME,null);

        return res;

    }


    ///////////formerly to-do fragment but now used as today fragment, same with to-do opportune which is now for tomorrow fragment
    public Cursor getTODOdata(int day,int month,int year)
    {
        Calendar daydate = Calendar.getInstance();


        //dates are stored normally(january starting form 1) in database and can be compared with the SQL format which also starts from 1.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//The YYYY-MM-DD DATE is a standard SQL data type, take note MM is month not mm

        String todayd=sdf.format(daydate.getTime());

        SQLiteDatabase db=this.getWritableDatabase();

        //  Cursor res=db.rawQuery("SELECT * from "+TABLE_NAME+" WHERE DAY="+day+" AND MONTH="+month+" AND YEAR="+year+" AND ISDONE='0'",null);
        Cursor res=db.rawQuery("SELECT * from "+TABLE_NAME+" WHERE TDATE= '"+todayd+"' AND ISDONE='0'",null);

        return res;

    }

    public Cursor getundoneproject()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+PROJECT_TABLE_NAME+" WHERE PERCENT<100 " ,null);

        return res;

    }

    public Cursor getundoneassignment()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+ASSIGNMENT_TABLE_NAME+" WHERE ISDONE='0' " ,null);

        return res;

    }

    public Cursor getcurrentdayTask(int day,int month,int year)//for home page
    {
        Calendar daydate = Calendar.getInstance();


        //dates are stored normally(january starting form 1) in database and can be compared with the SQL format which also starts from 1.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//The YYYY-MM-DD DATE is a standard SQL data type, take note MM is month not mm

        String todayd=sdf.format(daydate.getTime());

        SQLiteDatabase db=this.getWritableDatabase();

        //  Cursor res=db.rawQuery("SELECT * from "+TABLE_NAME+" WHERE DAY="+day+" AND MONTH="+month+" AND YEAR="+year+" AND ISDONE='0'",null);
        Cursor res=db.rawQuery("SELECT * from "+TABLE_NAME+" WHERE TDATE= '"+todayd+"' AND ISDONE='0'",null);

        return res;

    }


    public Cursor getnextdayTask(int day,int month,int year)//for home page
    {
        Calendar daydate = Calendar.getInstance();
        daydate.add(Calendar.DATE,1);

        //dates are stored normally(january starting form 1) in database and can be compared with the SQL format which also starts from 1.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//The YYYY-MM-DD DATE is a standard SQL data type, take note MM is month not mm

        String todayd=sdf.format(daydate.getTime());

        SQLiteDatabase db=this.getWritableDatabase();

        //  Cursor res=db.rawQuery("SELECT * from "+TABLE_NAME+" WHERE DAY="+day+" AND MONTH="+month+" AND YEAR="+year+" AND ISDONE='0'",null);
        Cursor res=db.rawQuery("SELECT * from "+TABLE_NAME+" WHERE TDATE= '"+todayd+"' AND ISDONE='0'",null);

        return res;

    }


    //
    public Cursor getnextsevendays()
    {
        //SQL dates are normal and january starts from 1

        Calendar daydate = Calendar.getInstance();

        Calendar next7daydate = Calendar.getInstance();
        next7daydate.add(Calendar.DATE,7);// add seven days to the current day


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//The YYYY-MM-DD DATE is a standard SQL data type, take note MM is month not mm

        String todayd=sdf.format(daydate.getTime());
        String next7d=sdf.format(next7daydate.getTime());

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+TABLE_NAME+" WHERE TDATE BETWEEN '"+todayd+"' AND '"+next7d+"' ORDER BY TDATE ASC",null);//sample date 2017-02-11, order aranges tasks so user can understand better

        return res;

    }

    //
    public Cursor getnext4weeks()
    {
        //SQL dates are normal and january starts from 1

        Calendar daydate = Calendar.getInstance();

        Calendar next7daydate = Calendar.getInstance();
        next7daydate.add(Calendar.DATE,28);// add seven days to the current day


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//The YYYY-MM-DD DATE is a standard SQL data type, take note MM is month not mm

        String todayd=sdf.format(daydate.getTime());
        String next7d=sdf.format(next7daydate.getTime());

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+TABLE_NAME+" WHERE TDATE BETWEEN '"+todayd+"' AND '"+next7d+"' ORDER BY TDATE ASC",null);//sample date 2017-02-11, order aranges tasks so user can understand better

        return res;

    }

    public Cursor getsavedtasks()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT TITLE from "+TABLE_NAME,null);

        return res;

    }


    public int deletetask(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"_id = ?",new String[]{id});
        //Cursor res=db.rawQuery("DELETE from "+TABLE_NAME+" WHERE _id="+id,null);

        //  return res;

    }


    public int deleteclass(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(CLASSES_TABLE_NAME,"_id = ?",new String[]{id});
        //Cursor res=db.rawQuery("DELETE from "+TABLE_NAME+" WHERE _id="+id,null);

        //  return res;

    }


    //
    public boolean insertCLASSdata(String title, String code, String credit, String venue, String lecturers, String starttime, String stoptime, String originaltime, String day)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(COL12,title);
        cv.put(COL13,code);
        cv.put(COL14,credit);
        cv.put(COL15,venue);
        cv.put(COL16,lecturers);
        cv.put(COL17,starttime);
        cv.put(COL18,stoptime);
        cv.put(COL19,originaltime);
        cv.put(COL20,day);

        long result= db.insert(CLASSES_TABLE_NAME,null,cv);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
            return true;
    }

    public Cursor getmondayclasss()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+CLASSES_TABLE_NAME+" WHERE DAY_C='Monday' ORDER BY ORIGINALTIME ASC",null);

        return res;

    }
    public Cursor gettuesdayclass()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+CLASSES_TABLE_NAME+" WHERE DAY_C='Tuesday' ORDER BY ORIGINALTIME ASC",null);

        return res;

    }

    public Cursor getwednesdayclass()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+CLASSES_TABLE_NAME+" WHERE DAY_C='Wednesday' ORDER BY ORIGINALTIME ASC",null);

        return res;

    }

    public Cursor getthursdayclass()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+CLASSES_TABLE_NAME+" WHERE DAY_C='Thursday' ORDER BY ORIGINALTIME ASC",null);

        return res;

    }

    public Cursor getfridayclass()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+CLASSES_TABLE_NAME+" WHERE DAY_C='Friday' ORDER BY ORIGINALTIME ASC",null);

        return res;

    }

    public Cursor getssaturdayclass()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("SELECT * from "+CLASSES_TABLE_NAME+" WHERE DAY_C='Saturday' ORDER BY ORIGINALTIME ASC",null);

        return res;

    }
}
