package ru.sber.game;

import java.util.ArrayList;
import java.util.List;

public class GameHelper {

    public List<Integer> moveAndMergeEqual(List<Integer> list) {

        List<Integer> resultList = new ArrayList<>();

        resultList = mergeEquals(move(list));

        return resultList;
    }

    private List<Integer> move(List<Integer> list) {

        List<Integer> resultList = new ArrayList<Integer>(list.size());
        int j = 0;

        for (int i = 0; i < list.size(); i++) {
            Integer a = list.get(i);
            if (a != null) {
                resultList.add(j++, a);
            }
        }

        for (int i = resultList.size(); i < list.size(); i++) {
            resultList.add(i, null);
        }

        return resultList;
    }

    private List<Integer> mergeEquals(List<Integer> list) {
        int sizeList = list.size();
        List<Integer> resultList = new ArrayList<Integer>(sizeList);

        boolean alreadyMerge = false;
        int j = 0;
        int i = 0;
        Integer a = list.get(i);
        resultList.add(j, a); i++;

        while ((i < list.size()) && (a != null)) {
            a = list.get(i);

            if (a != null) {
                j = resultList.size() - 1;
                if ((a == resultList.get(j)) && (!alreadyMerge)) {
                    resultList.set(j, (a * 2));
                    list.remove(i);
                    alreadyMerge = true;
                } else {
                    resultList.add(list.get(i++));
                    alreadyMerge = false;
                }

            }
        }

        for (int k = resultList.size(); k < sizeList; k++) {
            resultList.add(k, null);
        }

//        System.out.println(resultList);

        return resultList;
    }

}
