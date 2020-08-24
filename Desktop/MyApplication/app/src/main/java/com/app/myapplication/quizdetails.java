package com.app.myapplication;

import android.provider.BaseColumns;

public final class quizdetails {
    private quizdetails (){

    }
    public static class quiztable implements BaseColumns {
        public static final String table_name= "Question_detail";
        public static final String column_question = "question";
        public static final String column_option1 = "option1";
        public static final String column_option2 = "option2";
        public static final String column_answers = "answers";
    }
}
