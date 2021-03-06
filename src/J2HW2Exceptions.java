
/*
При считывании последний символ/число игнорируется. Не сумел использовать окончание файла для обработки последнего
введённого числа.
Использую символ "/", который игнорируется, но зато все строки и числа до него воспринимаются как надо.
На дополнительные числа или данные, как положено, срабатывает исключение о выходе за пределы массива.

Также при Неверном Формате Данных (в методе arraySum) трассировка стека ex.printStackTrace() выскакивает вразнобой.
Хотя, по идее, должна выводится после печати массива. Я закомментировал трассировку и вывод стал нормальным.
Как я понял, при выводе трассировки компилятор сам себе хозяин.

@author Grishin Dmitriy
@version dated 25.12.17
@link null
*/

import java.io.*; //импорт библиотеки ввода/вывода

public class J2HW2Exceptions {


    public static void main(String[] args) {
        String[][] arrayStr = new String[4][4];//создаю строчный массив


        try {
            FileReader file = new FileReader("source.txt");//открыл ресурс - file
            int i = 0, j = 0;//индексы массива: строка, столбец
            int b;
            String str = "";//создаю пустую строку

            //Заполнение массива:
            while ((b = file.read()) != -1){//посимвольное чтение из файла, пока не достигнет конца файла и тогда b примет значение -1
            //здесь метод read() возвращает int-значение числового кода считанного символа

                if ((char)b != ' '){//если символ - это пробел
                    //здесь приведение типов: int преобразуется в char
                    str = str + (char)b;//добавляю символ к строке

                } else {//заполняю элемент массива, если встретился пробел

                    try{
                        arrayStr[i][j] = str;
                    } catch (ArrayIndexOutOfBoundsException ex) { //Ловлю исключение на выход за пределы массива
                        System.out.println("Данные, начиная со строки " + str + " в файле, превышают размер массива 4 х 4");
                        //ex.printStackTrace();
                        break; //прекращаю цикл чтения файла
                    }

                    str = ""; //очищаю строку
                    j++;
                    if (j == 4){
                        i++;
                        j = 0;
                    }
                }
            }
            file.close();//закрыл ресурс - file
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Вывод массива:
        System.out.println("Массив, сформированный из файла: ");
        for (int i = 0; i < arrayStr.length ; i++) {
            for (int j = 0; j < arrayStr.length; j++) {
                System.out.print(arrayStr[i][j] + " ");
            }
           System.out.println();
        }

        System.out.println("Сумма чисел массива: " + arraySum(arrayStr));

    }

    // метод суммирования чисел:
    public static int arraySum (String[][] stringArray2D){
        int sum = 0;
        int x;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    x = Integer.parseInt(stringArray2D[i][j]);//преобразование строки в число
                    sum += x;
                } catch (NumberFormatException ex) {
                    //ex.printStackTrace();
                    System.out.println("В ячейке [" + i + "][" + j + "] лежит неверное данное: " + stringArray2D[i][j]);
                }

            }
        }
        return sum;
    }
}
