package com.test.util.list.listImpl;

/**
 * @className:
 * @Description:
 * @auther:ynhj
 * @date:10:04 2018-11-13
 * @version: ver 1.0
 */
public class Linear {
    private Element first;
    private Integer count = 0;

    public Linear(Object[] datas)  {
        first = new Element();
        first.data = datas[0];
        for (int i = 1; i < datas.length; i++) {
            Element next = first;
            for (int j = 0; j < i; j++) {
                 next = next.next;
            }
            next = new Element();
            next.data = datas[i];
        }
    }


    public Element get(Integer i) throws Exception {
        Element data = first;
        if (i > count) {
            throw new Exception();
        } else {
            for (int j = 0; j < i; j++) {
                data = first.next;
            }
        }
        return data;
    }

    public Integer length() {
        return count;
    }

    public Integer getLocate(Object data) {
        if (count < 1) {
            return null;
        } else {
            Element element = first;
            for (int i = 0; i < count; i++) {
                element = element.next;
                if (element.data.equals(data)){
                    return i;
                }
            }
            return null;
        }
    }

    public void insert(Object data, Integer local) throws Exception {
        if (local>count){
            throw new Exception();
        }else {
            Element original = first;
            for (int i = 0; i < local; i++) {
                original = first.next;
            }
            original.next = original;
            original.data = data;
            count++;
        }
    }

    public void delete(Object data) {

    }

    public boolean isEmpty() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private class Element {
        public Element previous;
        public Object data;
        public Element next;


    }

    public static void main(String[] args) {
        for (int i =                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              0; i < 0; i++) {
            System.out.println(1);
        }
    }

}
