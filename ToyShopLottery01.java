// Необходимо написать проект, для розыгрыша в магазине игрушек.
// Функционал должен содержать добавление новых игрушек и задания веса для выпадения игрушек.
//Напишите класс-конструктор у которого принимает минимум 3 строки, содержащие три поля
// id игрушки, текстовое название и частоту выпадения игрушки
//Из принятой строки id и частоты выпадения(веса) заполнить минимум три массива.
//Добавить элементы в коллекцию
//Организовать общую очередь добавления игрушек
//Вызвать метод 10 раз и записать результат в файл и\или вывести на экран.
//В программе должен быть минимум один класс со следующими свойствами:
// id игрушки,
// текстовое название,
// количество
// частота выпадения игрушки (вес в % от 100)
 
import java.io.*;
import java.lang.Math;
import java.util.*;
import java.time.format.*;
import java.time.LocalDateTime;


public class ToyShopLottery01 {
    public static void main(String[] args) {

        Toy toy01 = new Toy("A0258F5896", "Кукла Маша", 115,0, 0.0);
        Toy toy02 = new Toy("B5992D6578", "Кукла Наташа", 15,0, 0.0);
        Toy toy03 = new Toy("C8655S9857", "Грузовик большой", 53,0, 0.0);
        Toy toy04 = new Toy("V8654T9572", "Грузовик малый", 88,0, 0.0);
        Toy toy05 = new Toy("E0258O5896", "Конструктор деревянный", 48,0, 0.0);
        Toy toy06 = new Toy("D5992U6578", "Конструктор пластмассовый", 69,0, 0.0);
        Toy toy07 = new Toy("F8655I9857", "Пазл большой", 152,0, 0.0);
        Toy toy08 = new Toy("W8654P9572", "Пазл малый", 188,0, 0.0);    
        Toy toy09 = new Toy("F9687I5678", "Мяч большой", 203,0, 0.0);
        Toy toy10 = new Toy("W9857P5736", "Мяч малый", 118,0, 0.0);
      
        TreeMap<String,String> date_ID = new TreeMap<String,String>();
        TreeMap<String,String> id_name = new TreeMap<String,String>();
        TreeMap<String,Integer> id_freq = new TreeMap<String,Integer>();
        completion_treemap(date_ID, id_name,id_freq );

        ArrayList<Toy> listToys = new ArrayList<Toy>();
        listToys.add(toy01);
        listToys.add(toy02);
        listToys.add(toy03);
        listToys.add(toy04);
        listToys.add(toy05);
        listToys.add(toy06);
        listToys.add(toy07);
        listToys.add(toy08);
        listToys.add(toy09);
        listToys.add(toy10);

        sort_ArrayList(listToys);
        boolean fl = true;
        System.out.println("Запущена программа проведения лотереи в магазине игрушек\n" +
                 "--------------------------------------------------------");
        int number1 = 0;
        Scanner iScanner = new Scanner(System.in, "UTF-8");
        while(fl){
            LocalDateTime now = LocalDateTime.now();
            String date_str = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd.hh:mm:ss"));
            System.out.println("Введите номер необходимой операции : \n" +
                "1 - Просмотр базы игрушек \n" +
                "2 - Внесение изменений в базу игрушек \n" +
                "3 - Проведение лотереи\n" +
                "4 - Выдача выигрыша\n" + 
                "5 - Завершение программы");
            number1 = iScanner.nextInt();
            switch (number1) {
                case 1:
                    viewing_DB(listToys);
                    break;
                case 2 :
                    making_changes_DB(listToys, iScanner, id_name);
                    break;
                case 3 :
                    conducting_lottery(listToys,date_ID,id_name, date_str );
                    break;
                case 4 :
                    Payout_winnings(listToys, date_ID, id_name, iScanner, id_freq);
                    break;
                case 5 :
                    System.out.println("Завершение программы");
                    fl = false;
                    break;
                default:
                System.out.println("");
                    System.out.println("Указан недопустимый оператор.");
            }
        }
        iScanner.close();
    }

    public static void viewing_DB(ArrayList<Toy> listT){
        int count = 0;
        for ( Toy el:listT){
            printString(count, el.getname(), el.getquantity(), el.getfrequency());
            count ++;
            }
        }   
        
    public static void making_changes_DB(ArrayList<Toy> listToys, Scanner iSc,
                TreeMap<String,String> id_name ){
        boolean fl = true;
        int number1 = 0;  
        while(fl){
            System.out.println("Введите операции по внесению изменений в базу данных: \n" +
            "1 - добавить количество игрушек по ID \n" +
            "2 - внести новый вид игрушек  \n" +
            "3 - закончить изменения");
            number1 = iSc.nextInt();
            switch (number1) {
                case 1:
                    add_number_DB( listToys, iSc);
                    break;
                case 2 :
                    add_new_product_DB(listToys, iSc, id_name);
                    break;
                case 3 :
                    fl = false;
                    break;
                default:
                    System.out.println("Указан недопустимый оператор.");
                    break;
            }
        }
        return;
    }

    public static void conducting_lottery(ArrayList<Toy> listT, TreeMap<String,String> date_ID,
                     TreeMap<String,String> id_name, String date_str){
        Random random = new Random();
        int set_size = listT.size();
        boolean fl = true;
        while (fl) {
            int numb_winner = random.nextInt(set_size); 
            Toy str_DB = listT.get(numb_winner);
            int quantity = str_DB.getquantity();
            if (quantity >= 10) {
                quantity--;
                str_DB.setquantity(quantity);
                listT.set(numb_winner, str_DB); 
                System.out.println("Выиграла игрушка:");
                printString(numb_winner, str_DB.getname(), quantity, str_DB.getfrequency());
                String id = str_DB.getID();
                date_ID.put(date_str, id);
                String name = str_DB.getname();
                if (!id_name.containsKey(id)){
                    id_name.put(id, name);
                }   
                write_text_file(date_str, date_ID.get(date_str), id_name.get(date_ID.get(date_str)));
                fl = false;
            }
        }
        return;
    }

    public static void Payout_winnings(ArrayList<Toy> listT, TreeMap<String,String> date_ID,
                TreeMap<String, String> id_name, Scanner iSc,TreeMap<String, Integer> id_freq ){
        boolean fl = true; 
        String index = "0";
        String line;
        System.out.println("Список выигрышей для выдачи:");
        int count = 0;
        try {
            String id = "";
            File file01 = new File("file01.txt");
            File tmp = new File("tmp.txt");
            BufferedReader r1 = new BufferedReader(new FileReader(file01));
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(tmp,true));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter("file02.txt",true));
            for (Map.Entry<String, String>
                 entry : date_ID.entrySet()){
                System.out.println(count + ":" + entry.getKey() + ":" + entry.getValue() + ":" + id_name.get(entry.getValue()));
                count ++;
            }  
            if (file01.isFile()) {
                long size = file01.length();
                if (size == 0) {
                    System.out.println(" Файл на выдачу выигрышей пуст. Проведите лотерею.");
                    fl = false;
                }
            }
            while(fl){
                if (count > 1){
                    System.out.println("Укажите номер выдаваемого выигрыша:");
                    index = iSc.nextLine();
                    if ( index == ""){
                        index = iSc.nextLine();
                    }
                }
                if (isNumeric(index)){
                    int number =  Integer.parseInt(index);
                    if (number >= 0 && number < count ) {
                        fl = false;
                        int i = 0;
                        while ((line = r1 .readLine()) != null) {
                            line = line + "\n";
                            if ( i != number){
                                bw1.write(line);
                            } else {
                                bw2.write(line);
                                String[] subLines = line.split(";");
                                date_ID.remove(subLines[1]);
                                id = subLines[3];
                            }
                            i ++;
                        }
                        if (! fl){break; }
                    }
                    if (number >= count){
                        System.out.println("Указан неверный номер строки выигрыша ");
                        fl = false;
                    }
                }
            } 
            bw1.close();
            bw2.close();
            r1.close();

            if (tmp.isFile()){
                long size = tmp.length();
                if(size != 0){
                    frequency_calculation(listT, id, id_freq);
                    file01.delete();
                    tmp.renameTo(file01);
                }
            }
        }catch (IOException e) {
        System.out.println("Ошибки при чтении/записи файла ");
        } 
    }  
          
    public static void add_number_DB(ArrayList<Toy> listT, Scanner iSc){
        String str_number = "";
        int num = 0;
        int quantity_toy = 0;
        int add_number = 0;
        boolean fl = true;
        viewing_DB(listT);
        num = find_string_index(listT, iSc);
        if (num == -1) { return; }
        while (fl){
            System.out.println("Укажите, какое количество выбранного товара добавить? :");
            str_number = iSc.nextLine();
            if (isNumeric(str_number)){
                quantity_toy = Integer.parseInt (str_number);
                add_number = quantity_toy +  listT.get(num).getquantity();
                listT.get(num).setquantity(add_number);
                fl = false;
            } else {
                System.out.println("Неверно указано количество :)" + str_number);
            } 
        }
    }

    public static void add_new_product_DB(ArrayList<Toy> listT, Scanner iSc, 
            TreeMap<String,String> id_name){
        String str_name = "";
        String answer = "yYнН";
        String new_ID = "";
        boolean fl = true;
        boolean fl_ID = false;
        viewing_DB(listT);
        while (fl){
            System.out.println("Укажите наименование нового товара(ЛАТИНИЦА) :");
            str_name = iSc.nextLine();
            if (str_name == ""){
                str_name = iSc.nextLine();
            }
            while (!fl_ID){
                new_ID =  generating_random_String();
                if (!id_name.containsKey(new_ID)){
                    fl_ID = true;
                }
            }
            System.out.println("Сформирована строка по новому товару:");
            Toy new_toy = new Toy(new_ID, str_name, 0, 0, 0.0);
            printString(0, new_toy.getname(), new_toy.getquantity(), new_toy.getfrequency());
            System.out.println("Записать новый товар в БД (y/n)?");
            str_name = iSc.nextLine();
            if (answer.contains(str_name)){
                listT.add(new_toy);
                sort_ArrayList(listT);
                id_name.put(new_ID, new_toy.getname());
                return;
            } else {
                System.out.println("Прекратить ввод нового товара? (y/n) ");
                str_name = iSc.nextLine();
                if (answer.contains(str_name)){
                    fl = false;
                }
            }
        }
        return;
    }

    public static int find_string_index(ArrayList<Toy> listT, Scanner iSc){
        String numb = "";
        int number1 = 0;
        boolean  fl = true;
        String answer = "yYнН";
        while (fl){
            System.out.println("Введите индекс для поиска строки в БД(целое число): ");
            numb = iSc.nextLine();
            if ( numb == ""){
                numb = iSc.nextLine();
            };
            if (isNumeric(numb)){
                number1 = Integer.parseInt(numb); 
                if (number1 >= 0 && number1 < listT.size()){
                    Toy str_DB = listT.get(number1);
                    System.out.println("Найдена строка:");
                    printString(number1, str_DB.getname(), str_DB.getquantity(), str_DB.getfrequency());
                    fl = false;
                    return number1;
                } else {
                    System.out.printf("Неверно указан индекс строки (0 - %d)",(listT.size() - 1));
                } 
            } else {
                System.out.println("Прервать поиск строки (y/n)?"); 
                numb = iSc.nextLine();
                if (answer.contains(numb)){
                    return -1;
                }
            }
        }
        return -1;
    }
           
    public static void write_text_file(String date_now, String id, String name){
        try {
            File file = new File("file01.txt");
            String ru;
            StringBuilder sb = new StringBuilder();
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(file,true));
                sb.append("Дата;").append(date_now).append(";Идентификатор;")
                .append(id).append(";Наименование;").append(name).append("\n");
                ru = sb.toString();
                bw1.write(ru); 
                sb.setLength(0);
            bw1.close();
        } catch (IOException e) {
            System.out.println("Ошибки при чтении/записи файла ");
        } 
        return;
    }
   
    public static void frequency_calculation(ArrayList<Toy> listT, String id,TreeMap<String, Integer> id_freq){
        int count = 0;
        String ru;
        try {
            File file02 = new File("file02.txt");
            BufferedReader br = new BufferedReader(new FileReader(file02));
            while((ru = br.readLine())!=null) {  
                String[] subLines = ru.split(";");
                if (subLines[3].equals(id) ){
                    if (!id_freq.containsKey(id)){
                        id_freq.put(id, 1);
                    } else {
                        int n = id_freq.get(id) + 1;
                        id_freq.put(id, n);
                    }
                }
                count ++;
            }
            for ( Toy el:listT){
                int frequency = 0;
                boolean y_n = id_freq.containsKey(el.getID());
                if (y_n){
                    frequency = id_freq.get(el.getID());
                    Double num =  frequency + 0.0;
                    num = Math.round(num/count*100000.0)/1000. ;
                    el.setfrequency(num);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Ошибки при чтении/записи файла ");
        } 
        return;
    }

    public static void sort_ArrayList(ArrayList<Toy> listT){
        listT.sort(Comparator.comparing(Toy::getname).thenComparing(Toy::getID));
    }

    public static void printString(int index, String name, int quantity, Double frequency ) {
        System.out.printf( " %-5d : %-28s : %-5d : %-6.3f %n",
                                index, name, quantity,frequency);
    }

    public static boolean isNumeric(String string) { 
        if(string == null || string.equals("")) { 
            System.out.println("Введенная значение либо пустое, либо не является числом."); 
            return false; 
        } 
        try { 
            int intValue = Integer.parseInt(string); 
            return true; 
        } catch (NumberFormatException e) { 
            System.out.println("Введенная значение не является числом."); 
        } 
        return false; 
    } 

    public static String generating_random_String() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
    
        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
    
        System.out.println(generatedString);
        return generatedString;
    }

    public static void completion_treemap( TreeMap<String,String> date_ID,
                     TreeMap<String,String> id_name, TreeMap<String,Integer> id_freq){
        String ru;                
        try {
            File file02 = new File("file02.txt");
            File file01 = new File("file01.txt");
            BufferedReader br2 = new BufferedReader(new FileReader(file02));
            BufferedReader br1 = new BufferedReader(new FileReader(file01));
            while((ru = br1.readLine())!= null) {  
                String[] sl = ru.split(";");
                date_ID.put(sl[1], sl[3]);
                id_name.put(sl[3], sl[5]);
            }
            while((ru = br2.readLine())!= null) {  
                String[] sl = ru.split(";");
                if (id_freq.containsKey(sl[3] )) {
                    int  freq = id_freq.get(sl[3]);
                    freq ++;
                    id_freq.put(sl[3], freq);
                } else {
                    id_freq.put(sl[3], 1);
                }
            }
            br1.close();
            br2.close();
        } catch (IOException e) {
            System.out.println("Ошибки при чтении/записи файла ");
        } 
    }
}
