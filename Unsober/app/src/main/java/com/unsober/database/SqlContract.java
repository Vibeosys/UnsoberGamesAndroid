package com.unsober.database;

/**
 * Created by akshay on 07-07-2016.
 */
public class SqlContract {

    public abstract class SqlCategories {
        public static final String TABLE_NAME = "categories";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String ICON = "icon";
        public static final String PARENT_ID = "parent_id";
        public static final String STATUS = "status";
    }

    public abstract class SqlItems {
        public static final String TABLE_NAME = "items";
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String DESC = "description";
        public static final String IMG_LINK = "image_link";
        public static final String YOU_LINK = "youtube_link";
        public static final String NO_OF_PLAYERS = "number_of_players";
        public static final String TAG1 = "tag1";
        public static final String TAG2 = "tag2";
        public static final String TAG3 = "tag3";
        public static final String CATEGORY_ID = "category_id";
        public static final String STATUS = "status";
        public static final String DATE_TIME = "date_time";
        public static final String VIEWS = "views";
    }
}
