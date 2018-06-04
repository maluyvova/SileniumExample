package tests;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchItem {
    String name;
    float price;
    float score;

    public SearchItem(String name, float prize, float score) {
        this.name = name;
        this.price = prize;
        this.score = score;
    }

    public static void sortByPrice(List<SearchItem> list) {

        Comparator<SearchItem> comparatorPrice = new Comparator<SearchItem>() {
            @Override
            public int compare(SearchItem o1, SearchItem o2) {
                if(o1.price < o2.price) return -1;
                if(o1.price > o2.price) return 1;
                if(o1.price == o2.price) {
                    if(o1.score > o2.score) return -1;
                }
                return 1;
            }
        };
        Collections.sort(list, comparatorPrice);
    }

    public static void sortByScore(List<SearchItem> list) {

        Comparator<SearchItem> comparatorScore = new Comparator<SearchItem>() {
            @Override
            public int compare(SearchItem o1, SearchItem o2) {
                if(o1.score < o2.score) return -1;
                if(o1.score > o2.score) return 1;
                if(o1.score == o2.score) {
                    if(o1.price > o2.price) return -1;
                }
                return 1;
            }
        };
        Collections.sort(list, comparatorScore);
    }

}
