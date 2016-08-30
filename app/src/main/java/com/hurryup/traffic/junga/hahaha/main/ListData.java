package com.hurryup.traffic.junga.hahaha.main;

import android.graphics.drawable.Drawable;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by user on 2016-08-20.
 */
public class ListData {
    /**
     * 리스트 정보를 담고 있을 객체 생성
     */

    // 제목
    public String mTitle;
    // 주소
    public String mDate;

    /**
     * 알파벳 이름으로 정렬
     */
    public static final Comparator<ListData> ALPHA_COMPARATOR = new Comparator<ListData>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(ListData mListDate_1, ListData mListDate_2) {
            return sCollator.compare(mListDate_1.mTitle, mListDate_2.mTitle);
        }
    };
}