// 按键排序
Map<Character, Integer> map = new TreeMap<>(new Comparator<Character>() {
    public int compare(Character o1, Character o2) {
        return o2 - o1;
    }
});

// 按值排序
Map<Character, Integer> map = new HashMap<>();
List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
   public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
       return o2.getValue() - o1.getValue();
   } 
});

// 遍历
for (Map.Entry<K, V> entry: coursesMap.entrySet()) {  
    System.out.println(entry.getKey());  
    System.out.println(entry.getValue());  
}