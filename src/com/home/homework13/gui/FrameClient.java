package com.home.homework13.gui;

import com.home.homework13.dao.DaoAuto;
import com.home.homework13.dao.DaoUserOrder;
import com.home.homework13.database.DB;
import com.home.homework13.entity.Auto;
import com.home.homework13.entity.User;
import com.home.homework13.entity.UserOrder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameClient extends JFrame {

    private DB db;
    private JPanel panel;
    private Table table, table2;
    private JScrollPane scroll, scrollClientOrder;
    private JButton back, makeOrder;
    private User user;
    private JLabel tableAuto, clientOrders;

    public FrameClient(DB db, User user){
        this.user = user;   //!!!
        this.db = db;  //создаем подключение к DB
        setSize(400, 700);  //задаем размеры окна
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
        back.setPreferredSize(new Dimension(350, 35));  //задем размер кнопке Назад
        makeOrder = new JButton("Заказать");
        makeOrder.setPreferredSize(new Dimension(350, 35));

        tableAuto = new JLabel("Список автомобилей");
        clientOrders = new JLabel("Ваши заказы");

        DaoAuto daoAuto = new DaoAuto(db);  //вызываем класс DaoAuto для дальнейшего использовапния методов в DaoAuto
        table = new Table(daoAuto.getAll());  //создаем таблицу и помещаем в таблицу все содержимое таблицы Auto с помощью метода getAll, который находится в классе DaoAuto
        scroll = new JScrollPane(table);  //инициализируем scroll и помещаем на него нашу таблицу
        scroll.setPreferredSize(new Dimension(350, 200));  //задаем размер скрола

        DaoUserOrder daoUserOrder = new DaoUserOrder(db);
        table2 = new Table(daoUserOrder.getUserOrder(user.getId()));  //!!!
        scrollClientOrder = new JScrollPane(table2);
        scrollClientOrder.setPreferredSize(new Dimension(350, 200));

        panel.add(back);  //помещаем кнопку назад под таблицей
        panel.add(tableAuto);
        panel.add(scroll);  //помещаем скролл на панельку
        panel.add(makeOrder);
        panel.add(clientOrders);
        panel.add(scrollClientOrder);


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
        makeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DaoUserOrder(db).insert(new UserOrder(user.getId(),
                        Integer.valueOf(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)))));
                updateTable();
            }
        });



    }

    public void updateTable(){
        panel.remove(scroll);
        panel.remove(scrollClientOrder);

        DaoAuto daoAuto = new DaoAuto(db);  //вызываем класс DaoAuto для дальнейшего использовапния методов в DaoAuto
        table = new Table(daoAuto.getAll());  //создаем таблицу и помещаем в таблицу все содержимое таблицы Auto с помощью метода getAll, который находится в классе DaoAuto
        scroll = new JScrollPane(table);  //инициализируем scroll и помещаем на него нашу таблицу
        scroll.setPreferredSize(new Dimension(350, 200));  //задаем размер скрола

        DaoUserOrder daoUserOrder = new DaoUserOrder(db);
        table2 = new Table(daoUserOrder.getUserOrder(user.getId()));  //!!!
        scrollClientOrder = new JScrollPane(table2);
        scrollClientOrder.setPreferredSize(new Dimension(350, 200));

        panel.add(back);  //помещаем кнопку назад под таблицей
        panel.add(tableAuto);
        panel.add(scroll);  //помещаем скролл на панельку
        panel.add(makeOrder);
        panel.add(clientOrders);
        panel.add(scrollClientOrder);
        panel.updateUI();

    }

}
