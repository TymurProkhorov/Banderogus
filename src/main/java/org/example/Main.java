package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main extends TelegramLongPollingBot {
    private Map<Long, Integer> levels = new HashMap<>();

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Main());
    }

    @Override
    public String getBotUsername() {
        return "BanderogusBanderogus_go_it_bot";
    }

    @Override
    public String getBotToken() {
        return "6096680492:AAFJ5raNWKzL5ajPMZm51R8Sm-hUPtfUwrY";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);
        if (update.hasMessage() && update.getMessage().getText().equals("/start")) { // если есть сообщение и оно равно /start
            sendImage("level-1", chatId); // то отправляем пользователю картинку левел 1

            SendMessage message = createMessage("Ґа-ґа-ґа!\n" + // и сообщение для левел 1
                    "Вітаємо у боті біолабораторії «BanderoLab».\n" +
                    "\n" +
                    "Ти отримуєш гусака №" + new Random().nextInt(1000) +
                    "\n" +
                    "Цей бот ми створили для того, щоб твій гусак прокачався з рівня звичайної свійської худоби до рівня біологічної зброї, здатної нищити ворога. \n" +
                    "\n" +
                    "Щоб звичайний гусак перетворився на бандерогусака, тобі необхідно:\n" +
                    "✔️виконувати завдання\n" +
                    "✔️переходити на наступні рівні\n" +
                    "✔️заробити достатню кількість монет, щоб придбати Джавеліну і зробити свєрхтра-та-та.\n" +
                    "\n" +
                    "*Гусак звичайний.* Стартовий рівень.\n" +
                    "Бонус: 5 монет.\n" +
                    "Обери завдання, щоб перейти на наступний рівень");
            message.setChatId(chatId);
// присвоим переменной buttons список заданий и зарандомим их вызовом метода getRandom3, чтоб в бот попадали 3 случайные.
            List<String> buttons = Arrays.asList("Сплести маскувальну сітку (+15 монет)",
                    "Зібрати кошти патріотичними піснями (+15 монет)",
                    "Вступити в Міністерство Мемів України (+15 монет)",
                    "Запустити волонтерську акцію (+15 монет)",
                    "Вступити до лав тероборони (+15 монет)");
            buttons = getRandom3(buttons);

            // прикрепляем клаву и три кнопки
            attachButtons(message, Map.of(buttons.get(0), "level1Task",
                    buttons.get(1), "level1Task",
                    buttons.get(2), "level1Task"));
            sendApiMethodAsync(message);
        }
//--------------------второй уровень------------------------------------------
        if (update.hasCallbackQuery()) { // если была нажата любая кнопка
            // и если эта нажатая кнопка равна "level1Task"(т.е., любая из трех) и она является первым уровнем,
            if (update.getCallbackQuery().getData().equals("level1Task") && getLevel(chatId) == 1) {
                setLevel(chatId, 2); // то устанавливаем левел2. (Чтоб при повторных нажатиях на
                // таски первого уровня, второй не выскакивал каждый раз

                sendImage("level-2", chatId); // и отправляем картинку левел 2
                // и сообщение левела 2:
                SendMessage message = createMessage("*Вітаємо на другому рівні! Твій гусак - біогусак.*\n" +
                        "Баланс: 20 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень");
                message.setChatId(chatId);

                List<String> buttons = Arrays.asList("Зібрати комарів для нової біологічної зброї (+15 монет)",
                        "Пройти курс молодого бійця (+15 монет)",
                        "Задонатити на ЗСУ (+15 монет)",
                        "Збити дрона банкою огірків (+15 монет)",
                        "Зробити запаси коктейлів Молотова (+15 монет)");
                getRandom3(buttons);

                attachButtons(message, Map.of(buttons.get(0), "level2Task",
                        buttons.get(1), "level2Task",
                        buttons.get(2), "level2Task"));
                sendApiMethodAsync(message); // и отправляем
            }
        }
        // -----------------------третий уровень------------------------------
        if (update.hasCallbackQuery()) { // если была нажата любая кнопка
            // и если эта нажатая кнопка равна "level2Task"(т.е., любая из трех) и она является вторым уровнем,
            if (update.getCallbackQuery().getData().equals("level2Task") && getLevel(chatId) == 2) {
                setLevel(chatId, 3); // то устанавливаем левел3. (Чтоб при повторных нажатиях на
                // таски второго уровня, третий не выскакивал каждый раз

                sendImage("level-3", chatId); // и отправляем картинку левел 3
                // и сообщение левела 3:
                SendMessage message = createMessage("*Вітаємо на третьому рівні! Твій гусак - бандеростажер.*\n" +
                        "Баланс: 35 монет. \n" +
                        "Обери завдання, щоб перейти на наступний рівень");
                message.setChatId(chatId);

                List<String> buttons = Arrays.asList("Злітати на тестовий рейд по чотирьох позиціях (+15 монет)",
                        "Відвезти гуманітарку на передок (+15 монет)",
                        "Знайти зрадника та здати в СБУ (+15 монет)",
                        "Навести арту на орків (+15 монет)",
                        "Притягнути танк трактором (+15 монет)");

                getRandom3(buttons);

                attachButtons(message, Map.of(buttons.get(0), "level3Task",
                        buttons.get(1), "level3Task",
                        buttons.get(2), "level3Task"));
                sendApiMethodAsync(message); // и отправляем
            }
        }
        //------------------четвертый уровень--------------------
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("level3Task") && getLevel(chatId) == 3) {
                setLevel(chatId, 4);
                sendImage("level-4", chatId);

                SendMessage message = createMessage("*Вітаємо на останньому рівні! Твій гусак - готова біологічна зброя - бандерогусак.*\n" +
                        "Баланс: 50 монет.\n" +
                        "Тепер ти можеш придбати Джавелін і глушити чмонь");
                message.setChatId(chatId);

                attachButtons(message, Map.of("Купити Джавелін (50 монет)", "level4Task"));
                sendApiMethodAsync(message);
            }
        }
        //------------------пятый уровень---------------------------
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("level4Task") && getLevel(chatId) == 4) {
                setLevel(chatId, 5);
                SendMessage message = createMessage("*Джавелін твій. Повний вперед!*");
                message.setChatId(chatId);
                sendImage("final", chatId);
                sendApiMethodAsync(message);
            }
        }
    }

    public Long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        return null;
    }

    public SendMessage createMessage(String text) {
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
        return message;
    }

    public void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);
            keyboard.add(Arrays.asList(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    public void sendImage(String name, Long chatId) { // отправим картинку по имени name, кому? - боту(chatId)
        SendAnimation animation = new SendAnimation();

        InputFile inputFile = new InputFile();
        inputFile.setMedia(new File("images/" + name + ".gif"));

        animation.setAnimation(inputFile);
        animation.setChatId(chatId);

        executeAsync(animation);

    }

    public int getLevel(Long chatId) {
        return levels.getOrDefault(chatId, 1);
    }

    public void setLevel(Long chatId, int level) {
        levels.put(chatId, level);
    }

    public List<String> getRandom3(List<String> options) {
        ArrayList<String> copy = new ArrayList<>(options);
        Collections.shuffle(copy);
        return copy.subList(0, 3);
    }
}