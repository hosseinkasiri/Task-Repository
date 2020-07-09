package com.example.task.database;

import java.util.UUID;

public class TaskDbSchema {
    public static final String NAME = "task.db";
    public static final int VERSION = 1;

    public static final class UserTable{
        public static final String NAME = "user";
        public static final class UserCols{
            public static final String UUID = "UserUuid";
            public static final String FIRST_NAME = "firstName";
            public static final String LAST_NAME = "lastName";
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
            public static final String GUEST = "guest";
        }
    }

    public static final class TaskTable{
        public static final String NAME = "task";
        public static final class TaskCols{
            public static final String UUID = "taskUuid";
            public static final String TITLE = "taskTitle";
            public static final String DESCRIPTION = "taskDescription";
            public static final String DATE = "taskDate";
            public static final String DONE = "taskDone";
            public static final String USER_ID = "user_id";
        }
    }
}
