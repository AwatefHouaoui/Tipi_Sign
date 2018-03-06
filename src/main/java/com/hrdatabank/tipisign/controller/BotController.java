package com.hrdatabank.tipisign.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.DatetimePickerAction;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Response;

@Slf4j
@LineMessageHandler
@RestController
public class BotController {
	Logger logger = LoggerFactory.getLogger(BotController.class);

	@Autowired
	private LineMessagingClient lineMessagingClient;
	String channelToken = "YeGR+Tx/wXpHTEto2B3faYXy16D+9ACtLW7yiUqqDAtFeX0nW5AEgHNcUYfxDL4+5QQ1ytWFUv1Ol5+1Pb2wOWk5+44idmCjlP6vancpqEk6q0tNuG4GGOPg5/S/mE4HZiqkBcEJ4F5plHAxfQ0CTwdB04t89/1O/w1cDnyilFU=";

	@EventMapping
	public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
		TextMessageContent message = event.getMessage();
		handleTextContent(event.getReplyToken(), event, message);
	}

	@RequestMapping(value = "/service", method = RequestMethod.GET)
	private String serviceEssai() {
		return "just here now";

	}

	private static String createUri(String path) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(path).build().toUriString();
	}

	@RequestMapping(value = "/webhook", method = RequestMethod.POST)
	private @ResponseBody Map<String, Object> webhook(@RequestBody Map<String, Object> obj)
			throws JSONException, IOException {

		Map<String, Object> json = new HashMap<>();

		JSONObject jsonResult = new JSONObject(obj);
		JSONObject rsl = jsonResult.getJSONObject("originalRequest");
		JSONObject data = rsl.getJSONObject("data");
		JSONObject source = data.getJSONObject("source");
		JSONObject message = data.getJSONObject("message");
		String userId = source.getString("userId");
		String customerMessage = message.getString("text");
		String timestamp = jsonResult.getString("timestamp");
		JSONObject result = jsonResult.getJSONObject("result");
		JSONObject metadata = result.getJSONObject("metadata");
		String intentName = metadata.getString("intentName");
		JSONObject parameters = result.getJSONObject("parameters");
		JSONObject fulfillment = result.getJSONObject("fulfillment");
		String speech = fulfillment.getString("speech");

		LinkedHashMap<String, String> hm = new LinkedHashMap<>();
		System.out.println("test");
		// switch (intentName.toLowerCase()) {
		// case "carousel":
		//
		// String imageUrl = createUri("/static/buttons/1040.jpg");
		// CarouselTemplate carouselTemplate = new CarouselTemplate(Arrays.asList(
		// new CarouselColumn(imageUrl, "hoge", "fuga",
		// Arrays.asList(new URIAction("Go to line.me", "https://line.me"),
		// new URIAction("Go to line.me", "https://line.me"),
		// new PostbackAction("Say hello1", "hello こんにちは"))),
		// new CarouselColumn(imageUrl, "hoge", "fuga",
		// Arrays.asList(new PostbackAction("言 hello2", "hello こんにちは", "hello こんにちは"),
		// new PostbackAction("言 hello2", "hello こんにちは", "hello こんにちは"),
		// new MessageAction("Say message", "Rice=米"))),
		// new CarouselColumn(imageUrl, "Datetime Picker", "Please select a date, time
		// or datetime",
		// Arrays.asList(
		// new DatetimePickerAction("Datetime", "action=sel", "datetime",
		// "2017-06-18T06:15",
		// "2100-12-31T23:59", "1900-01-01T00:00"),
		// new DatetimePickerAction("Date", "action=sel&only=date", "date",
		// "2017-06-18",
		// "2100-12-31", "1900-01-01"),
		// new DatetimePickerAction("Time", "action=sel&only=time", "time", "06:15",
		// "23:59",
		// "00:00")))));
		// TemplateMessage templateMessage = new TemplateMessage("Carousel alt text",
		// carouselTemplate);
		// PushMessage pushMessage = new PushMessage(userId, templateMessage);
		// LineMessagingServiceBuilder.create(channelToken).build().pushMessage(pushMessage).execute();
		//
		// break;
		//
		// case "menu":
		//
		// hm.put("Osaka", "osaka");
		// hm.put("Tokyo", "tokyo");
		// hm.put("London", "london");
		//
		// typeBRecursiveChoices(
		// "https://lh3.googleusercontent.com/oKsgcsHtHu_nIkpNd-mNCAyzUD8xo68laRPOfvFuO0hqv6nDXVNNjEMmoiv9tIDgTj8=w170",
		// " boldTitle", " normalTitle", hm, channelToken, userId);
		// logger.info("paris :" + customerMessage);
		//
		// break;
		// case "type b":
		//
		// typeBRecursiveChoices(
		// "https://lh3.googleusercontent.com/oKsgcsHtHu_nIkpNd-mNCAyzUD8xo68laRPOfvFuO0hqv6nDXVNNjEMmoiv9tIDgTj8=w170",
		// " boldTitle", " normalTitle", hm, channelToken, userId);
		// logger.info("see more :", customerMessage);
		// break;
		// case "b2":
		// hm = new LinkedHashMap<>();
		// hm.put("Osaka", "osaka");
		// hm.put("Tokyo", "tokyo");
		// hm.put("London", "london");
		// typeBChoices(
		// "https://lh3.googleusercontent.com/oKsgcsHtHu_nIkpNd-mNCAyzUD8xo68laRPOfvFuO0hqv6nDXVNNjEMmoiv9tIDgTj8=w170",
		// " boldTitle", " normalTitle", hm, " nextOrSeeMore", " nextOrSeeMoreAnswer",
		// channelToken, userId);
		// logger.info("London :", customerMessage);
		// break;
		//
		// default:
		// sendAlertViaSlack(userId, timestamp, customerMessage);
		// logger.info("slack :" + customerMessage);
		// break;
		// }

		return json;

	}

	/**
	 * Map<String, Object> webhook(@RequestBody Map<String, Object> obj) throws
	 * JSONException, IOException { //Map<String, Object> json = new HashMap<String,
	 * Object>();
	 * 
	 * return obj; }
	 * 
	 * 
	 **/

	@EventMapping
	public void handlePostbackEvent(PostbackEvent event) {
		String replyToken = event.getReplyToken();
		logger.info(replyToken, "Got postback data ", event.getPostbackContent().getData(), ", param ",
				event.getPostbackContent().getParams().toString());
	}

	private void handleTextContent(String replyToken, Event event, TextMessageContent content) throws Exception {
		String text = content.getText();
		String channelToken = "";
		logger.info("Got text message from {}: {}" + replyToken + text);
		switch (text) {
		case "paris": {
			String userId = event.getSource().getUserId();
			if (userId != null) {

				typeDQuestion(
						"https://lh3.googleusercontent.com/oKsgcsHtHu_nIkpNd-mNCAyzUD8xo68laRPOfvFuO0hqv6nDXVNNjEMmoiv9tIDgTj8=w170",
						channelToken, userId);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				logger.info(content.toString());

			}
			break;
		}
		default:
			logger.info("Returns echo message {}: {}" + replyToken + text);
			logger.info(replyToken + text);
			break;
		}

	}

	/**
	 * Method for send carousel type A template message to use
	 * 
	 * @param userId
	 * @param lChannelAccessToken
	 * @param nameSatff1
	 * @param nameSatff2
	 * @param poster1_url
	 * @param poster2_url
	 * @throws IOException
	 */
	private void carouselForUser(String userId, String lChannelAccessToken, java.util.List<String> randomLinks)
			throws IOException {

		java.util.List<CarouselColumn> columns = new ArrayList<>();

		for (String link : randomLinks) {
			Document doc = Jsoup.connect(link).get();
			String title = doc.getElementsByClass("tit_articleName").get(0).text();
			String img = doc.getElementsByClass("max-width-260").get(0).attr("abs:src");

			CarouselColumn column = new CarouselColumn(img, title, "Click check for more details",
					Arrays.asList(new URIAction("check", link)));
			columns.add(column);
		}

		CarouselTemplate carouselTemplate = new CarouselTemplate(columns);

		TemplateMessage templateMessage = new TemplateMessage("Your search result", carouselTemplate);
		PushMessage pushMessage = new PushMessage(userId, templateMessage);
		try {
			Response<BotApiResponse> response = LineMessagingServiceBuilder.create(lChannelAccessToken).build()
					.pushMessage(pushMessage).execute();
			logger.info(response.code() + " " + response.message());
		} catch (IOException e) {
			logger.info("Exception is raised ");
			e.printStackTrace();
		}
	}

	/**
	 * TypeC template
	 * 
	 * @param userId
	 * @param channelToken
	 * @param msgTemplate
	 * @param msgFirstAnswer
	 * @param msgSecondAnswer
	 * @param titleTemplate
	 * @throws IOException
	 */
	public void typeCQuestion(String msgTemplate, String msgFirstAnswer, String msgFirstAnswerToSend,
			String msgSecondAnswer, String msgSecondAnswerToSend, String titleTemplate, String channelToken,
			String userId) throws IOException {
		ConfirmTemplate confirmTemplate = new ConfirmTemplate(msgTemplate,
				new MessageAction(msgFirstAnswer, msgFirstAnswerToSend),
				new MessageAction(msgSecondAnswer, msgSecondAnswerToSend));
		TemplateMessage templateMessage = new TemplateMessage(titleTemplate, confirmTemplate);
		PushMessage pushMessage = new PushMessage(userId, templateMessage);
		LineMessagingServiceBuilder.create(channelToken).build().pushMessage(pushMessage).execute();

	}

	/**
	 * TypeB template
	 * 
	 * @param userId
	 * @param channelToken
	 * @param imageURL
	 * @param boldTitle
	 * @param normalTitle
	 * @param buttonHint
	 * @param messageToSend
	 * @throws IOException
	 */
	public void typeBQuestion(String imageURL, String boldTitle, String normalTitle, String buttonHint,
			String messageToSend, String channelToken, String userId) throws IOException {
		ButtonsTemplate buttonsTemplate = new ButtonsTemplate(imageURL, boldTitle, normalTitle, Arrays.asList(
				new URIAction("Go to line.me", "https://line.me"), new MessageAction(buttonHint, messageToSend)));
		TemplateMessage templateMessage = new TemplateMessage("Button alt text", buttonsTemplate);
		PushMessage pushMessage = new PushMessage(userId, templateMessage);
		LineMessagingServiceBuilder.create(channelToken).build().pushMessage(pushMessage).execute();
	}

	/**
	 * TypeB template
	 * 
	 * @param userId
	 * @param channelToken
	 * @param imageURL
	 * @param boldTitle
	 * @param normalTitle
	 * @param buttonHint
	 * @param messageToSend
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public void typeBChoices(String imageURL, String boldTitle, String normalTitle, LinkedHashMap<String, String> hm,
			String nextOrSeeMore, String nextOrSeeMoreAnswer, String channelToken, String userId) throws IOException {
		List<Action> messageActions = new ArrayList<>();
		for (Map.Entry m : hm.entrySet()) {
			MessageAction ma = new MessageAction(m.getKey().toString(), m.getValue().toString());
			messageActions.add(ma);
		}
		messageActions.add(new MessageAction(nextOrSeeMore, nextOrSeeMoreAnswer));
		ButtonsTemplate buttonsTemplate = new ButtonsTemplate(imageURL, boldTitle, normalTitle, messageActions);

		TemplateMessage templateMessage = new TemplateMessage("Button alt text", buttonsTemplate);
		PushMessage pushMessage = new PushMessage(userId, templateMessage);
		LineMessagingServiceBuilder.create(channelToken).build().pushMessage(pushMessage).execute();
	}

	/**
	 * TypeD template Date-Calendar
	 * 
	 * @param userId
	 * @param channelToken
	 * @param imageURL
	 * @param boldTitle
	 * @param normalTitle
	 * @param buttonHint
	 * @param messageToSend
	 * @throws IOException
	 */
	public void typeDQuestion(String imageUrl, String channelToken, String userId) throws IOException {
		logger.info("CALENDAAAAAAAAAAAARRRR");
		DatetimePickerAction date = new DatetimePickerAction("Date", "action=sel", "date");
		CarouselTemplate carouselTemplate = new CarouselTemplate(Arrays.asList(
				new CarouselColumn("https://cdn2.iconfinder.com/data/icons/employment-business/256/Job_Search-512.png",
						"Datetime Picker", "Please select a date, time or datetime", Arrays.asList(date))));
		TemplateMessage templateMessage1 = new TemplateMessage("date time picker", carouselTemplate);
		ReplyMessage pushMessage1 = new ReplyMessage(userId, templateMessage1);
		logger.info("push message is \n " + pushMessage1);
		logger.info(templateMessage1.getTemplate().toString());
		LineMessagingServiceBuilder.create(channelToken).build().replyMessage(pushMessage1);
		logger.info("response is \n " + Arrays.toString(pushMessage1.getMessages().toArray()));

	}

	public void typeBRecursiveChoices(String imageURL, String boldTitle, String normalTitle, Map<String, String> hm,
			String channelToken, String userId) throws IOException {
		List<Action> messageActions = new ArrayList<>();
		hm.size();
		if (hm.size() <= 3) {
			for (Map.Entry m : hm.entrySet()) {
				MessageAction ma = new MessageAction(m.getKey().toString(), m.getValue().toString());
				messageActions.add(ma);
			}
			messageActions.add(new MessageAction("N/A", "not available"));
			ButtonsTemplate buttonsTemplate = new ButtonsTemplate(imageURL, boldTitle, normalTitle, messageActions);

			TemplateMessage templateMessage = new TemplateMessage("Button alt text", buttonsTemplate);
			PushMessage pushMessage = new PushMessage(userId, templateMessage);
			LineMessagingServiceBuilder.create(channelToken).build().pushMessage(pushMessage).execute();

		}
	}

}
