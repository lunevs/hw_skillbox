package app;

import app.config.AppConfig;
import app.service.ContactsLoader;
import app.service.PhoneBookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        PhoneBookService phoneBookService = context.getBean(PhoneBookService.class);
        ContactsLoader contactsLoader = context.getBean(ContactsLoader.class);
        contactsLoader.initContacts();

        printMenu();

        label:
        while (true) {
            System.out.print("Введите команду: ");
            String readLine = scanner.nextLine();
            String[] command = readLine.split("\\s+", 2);

            switch (command[0]) {
                case "exit":
                    break label;
                case "add":
                    if (command.length > 1) {
                        phoneBookService.parseAndAdd(command[1]);
                    } else {
                        System.out.println("incorrect command, you need to specify arguments");
                    }
                    break;
                case "remove":
                    if (command.length > 1) {
                        phoneBookService.remove(command[1]);
                    } else {
                        System.out.println("incorrect command, you need to specify arguments");
                    }
                    break;
                case "list":
                    phoneBookService.print();
                    break;
                case "dump":
                    phoneBookService.dumpContracts();
                    break;
                default:
                    printMenu();
                    break;
            }
        }
        ((AnnotationConfigApplicationContext) context).close();
    }

    private static void printMenu() {
        String menu = """
                Доступные команды:
                \tadd - добавить / обновить Контакт (ключ - Email). Формат: add ФИО;телефон (+79991234567);email
                \tremove - удалить контакт по Email. Формат: remove email
                \tlist - вывести список текущих Контактов в базе (вызывается без параметров)
                \tdump - сохранить все текущие Контакты в файл, при этом файл будет перезаписан
                \texit - выход из программы

                """;
        System.out.println(menu);
    }
}
