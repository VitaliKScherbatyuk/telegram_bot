/*
 * @author scherbatyuk
 * create in 2023
 * for portfolio
 */

package TelegramBot.scherbatyuk;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class App extends TelegramLongPollingBot {

	private Map<Long, Integer> levels = new HashMap<>();

	public static void main(String[] args) throws TelegramApiException {

		TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
		api.registerBot(new App());
	}

	/**
	 * extends method for set username bot telegam
	 */
	public String getBotUsername() {
		return "Your username";
	}

	/**
	 * extends method for set token bot telegam
	 */
	@Override
	public String getBotToken() {
		return "Your token";
	}

	/**
	 * extends method for update bot telegram
	 */
	public void onUpdateReceived(Update update) {
		Long chatId = getChatId(update);

		// -1-
//		SendMessage msg = createMessage("Hello, *Vitalik*");
//		
//		attachButtons(msg, Map.of(
//				"One", "one_btn",
//				"Two", "two_btn"
//				) );
//		
//		msg.setChatId(chatId);
//		sendApiMethodAsync(msg);

		// -2-
//		if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
//			SendMessage message = createMessage("Привіт!");
//			message.setChatId(chatId);
//			attachButtons(message, Map.of("Слава Україні!", "btn1"));
//			sendApiMethodAsync(message);
//
//		}
//
//		if (update.hasCallbackQuery()) {
//			if (update.getCallbackQuery().getData().equals("btn1")) {
//				SendMessage message = createMessage("Героям Слава!");
//				message.setChatId(chatId);
//				attachButtons(message, Map.of("Слава Нації!", "btn2"));
//				sendApiMethodAsync(message);			
//			}
//			
//			if (update.hasCallbackQuery()) {
//				if (update.getCallbackQuery().getData().equals("btn2")) {
//					SendMessage message = createMessage("Смерть ворогам!");
//					message.setChatId(chatId);
//					sendApiMethodAsync(message);
//				}
//			}
//		}

		// -3-
		if (update.hasMessage() && update.getMessage().getText().equals("/start")) {

			// Send image for level - 1
			sendImage("level-1", chatId);

			// Send message for level - 1
			SendMessage message = createMessage("Ґа-ґа-ґа!\r\n"
					+ "Вітаємо у боті біолабораторії «Батько наш Бандера».\r\n" + "\r\n" + "Ти отримуєш гусака №71\r\n"
					+ "\r\n"
					+ "Цей бот ми створили для того, щоб твій гусак прокачався з рівня звичайної свійської тварини до рівня біологічної зброї, здатної нищити ворога. \r\n"
					+ "\r\n" + "Щоб звичайний гусак перетворився на бандерогусака, тобі необхідно:\r\n"
					+ "✔️виконувати завдання\r\n" + "✔️переходити на наступні рівні\r\n"
					+ "✔️заробити достатню кількість монет, щоб придбати Джавеліну і зробити свєрхтра-та-та.\r\n"
					+ "\r\n" + "*Гусак звичайний.* Стартовий рівень.\r\n" + "Бонус: 5 монет.\r\n"
					+ "Обери завдання, щоб перейти на наступний рівень");

			message.setChatId(chatId);

			List<String> buttons_1 = Arrays.asList("Сплести маскувальну сітку (+15 монет)",
					"Зібрати кошти патріотичними піснями (+15 монет)",
					"Вступити в Міністерство Мемів України (+15 монет)", "Запустити волонтерську акцію (+15 монет)",
					"Вступити до лав тероборони (+15 монет)");

			buttons_1 = random_3_variants(buttons_1);

			attachButtons(message, Map.of(buttons_1.get(0), "level1_task", buttons_1.get(1), "level1_task",
					buttons_1.get(2), "level1_task"));
			sendApiMethodAsync(message);
		}

		if (update.hasCallbackQuery()) {

			if (update.getCallbackQuery().getData().equals("level1_task") && getLevel(chatId) == 1) {

				setLevel(chatId, 2);
				sendImage("level-2", chatId);

				List<String> buttons_2 = Arrays.asList("Зібрати комарів для нової біологічної зброї (+15 монет)",
						"Пройти курс молодого бійця (+15 монет)", "Задонатити на ЗСУ (+15 монет)",
						"Збити дрона банкою огірків (+15 монет)", "Зробити запаси коктейлів Молотова (+15 монет)");

				SendMessage message = createMessage("*Вітаємо на другому рівні! Твій гусак - біогусак.*\r\n"
						+ "Баланс: 20 монет. \r\n" + "Обери завдання, щоб перейти на наступний рівень");
				message.setChatId(chatId);
				attachButtons(message, Map.of(buttons_2.get(0), "level2_task", buttons_2.get(1), "level2_task",
						buttons_2.get(2), "level2_task"));
				sendApiMethodAsync(message);
			}

			if (update.getCallbackQuery().getData().equals("level2_task") && getLevel(chatId) == 2) {

				setLevel(chatId, 3);
				sendImage("level-3", chatId);

				List<String> buttons_3 = Arrays.asList("Злітати на тестовий рейд по чотирьох позиціях (+15 монет)",
						"Відвезти гуманітарку на передок (+15 монет)", "Знайти зрадника та здати в СБУ (+15 монет)",
						"Навести арту на орків (+15 монет)", "Притягнути танк трактором (+15 монет)");

				SendMessage message = createMessage("*Вітаємо на третьому рівні! Твій гусак - бандеростажер.*\r\n"
						+ "Баланс: 35 монет. \r\n" + "Обери завдання, щоб перейти на наступний рівень");
				message.setChatId(chatId);
				attachButtons(message, Map.of(buttons_3.get(0), "level3_task", buttons_3.get(1), "level3_task",
						buttons_3.get(2), "level3_task"));
				sendApiMethodAsync(message);
			}

			if (update.getCallbackQuery().getData().equals("level3_task") && getLevel(chatId) == 3) {

				setLevel(chatId, 4);
				sendImage("level-4", chatId);

				SendMessage message = createMessage(
						"*Вітаємо на останньому рівні! Твій гусак - готова біологічна зброя - бандерогусак.*\r\n"
								+ "Баланс: 50 монет. \r\n" + "Тепер ти можеш придбати Джавелін і глушити чмонь");
				message.setChatId(chatId);
				attachButtons(message, Map.of("Купити Джавелін (50 монет)", "level4_task"));
				sendApiMethodAsync(message);
			}

			if (update.getCallbackQuery().getData().equals("level4_task") && getLevel(chatId) == 4) {

				setLevel(chatId, 5);
				SendMessage message = createMessage("*Джавелін твій. Повний вперед!*");
				message.setChatId(chatId);
				sendApiMethodAsync(message);

				sendImage("final", chatId);
			}
		}
	}

	/**
	 * method for create chart for working different users
	 * 
	 * @param update
	 * @return
	 */
	public Long getChatId(Update update) {
		if (update.hasMessage()) {
			return update.getMessage().getFrom().getId();
		}
		if (update.hasCallbackQuery()) {
			return update.getCallbackQuery().getFrom().getId();
		}
		return null;
	}

	/**
	 * method for create mark in message
	 * 
	 * @param text
	 * @return
	 */
	public SendMessage createMessage(String text) {
		SendMessage message = new SendMessage();
		message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
		message.setParseMode("markdown");

		return message;
	}

	/**
	 * method for using list buttons
	 * 
	 * @param message
	 * @param buttons
	 */
	public void attachButtons(SendMessage message, Map<String, String> buttons) {
		InlineKeyboardMarkup muMarkup = new InlineKeyboardMarkup();

		// create list buttons and get value each buttons
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
		for (String buttonName : buttons.keySet()) {
			String buttonValue = buttons.get(buttonName);

			InlineKeyboardButton button = new InlineKeyboardButton();
			button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
			button.setCallbackData(buttonValue);
			keyboard.add(Arrays.asList(button));
		}
		muMarkup.setKeyboard(keyboard);
		message.setReplyMarkup(muMarkup);
	}

	/**
	 * method for sending a image to a specific id chat
	 * 
	 * @param name
	 * @param chatId
	 */
	public void sendImage(String name, Long chatId) {
		SendAnimation animation = new SendAnimation();

		InputFile inputFile = new InputFile();
		inputFile.setMedia(new File("image/" + name + ".gif"));
		animation.setAnimation(inputFile);
		animation.setChatId(chatId);

		executeAsync(animation);
	}

	/**
	 * method to determine the level for a user by its id. If the user is new, the
	 * default value is set to level 1.
	 * 
	 * @param chatId
	 * @return
	 */
	public int getLevel(Long chatId) {
		return levels.getOrDefault(chatId, 1);
	}

	/**
	 * method for a registered user that gets the entire level by id
	 * 
	 * @param chatId
	 * @param level
	 */
	public void setLevel(Long chatId, int level) {
		levels.put(chatId, level);
	}

	/**
	 * method for take random 3 answers
	 * 
	 * @param variant
	 * @return
	 */
	public List<String> random_3_variants(List<String> variant) {
		ArrayList<String> copy = new ArrayList<String>(variant);
		Collections.shuffle(copy);
		return copy.subList(0, 3);
	}
}
