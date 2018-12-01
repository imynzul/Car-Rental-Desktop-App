package com.home.homework13.gui;

import com.home.homework13.dao.DaoAuto;
import com.home.homework13.database.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameClient extends JFrame {

    private DB db;
    private JPanel panel;
    private Table table;
    private JScrollPane scroll;
    private JButton back;

    public FrameClient(DB db){
        this.db = db;  //создаем подключение к DB
        setSize(1000, 600);  //задаем размеры окна
        setTitle("Страница Клиента");  //даем название странице - заголовок
        setLocationRelativeTo(null);  //помещаем страницу в центр
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //по нажатию на крестик страница будет закрываться
        initComponents();  //добавляем все компоенты на окна, компоненты мы создаем ниже
        activation();  //активируем наши компоненты, активацию создаем ниже

        setVisible(true);  //делаем окно видимым
    }

    public void initComponents(){
        panel = new JPanel();  //инициализируем панельку, на которую мы будем помещать компоненты страницы
        back = new JButton("Назад");  //инициализируем кнопку назад, даем ей название

        DaoAuto daoAuto = new DaoAuto(db);  //вызываем класс DaoAuto для дальнейшего использовапния методов в DaoAuto
        table = new Table(daoAuto.getAll());  //создаем таблицу и помещаем в таблицу все содержимое таблицы Auto с помощью метода getAll, который находится в классе DaoAuto
        scroll = new JScrollPane(table);  //инициализируем scroll и помещаем на него нашу таблицу
        scroll.setPreferredSize(new Dimension(920, 200));  //задаем размер скрола

        back.setPreferredSize(new Dimension(150, 35));  //задем размер кнопке Назад

        panel.add(scroll);  //помещаем скролл на панельку
        panel.add(back);  //помещаем кнопку назад под таблицей

        add(panel);  //добавляем панельку на окно
    }

    public void activation(){
        back.addActionListener(new ActionListener() {  //активируем кнопку Назад
            @Override
            public void actionPerformed(ActionEvent e) {
                new Authorization(db);  //создаем новое окно Авторизации
                dispose();  //закрываем старое окно Клиента
            }
        });



    }

}
