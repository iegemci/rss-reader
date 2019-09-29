package com.enesgemci.rssreader.rss;

import android.text.TextUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class XmlParser {

    public static ArrayList<Article> parse(String xml) throws XmlPullParserException, IOException {
        ArrayList<Article> list = new ArrayList<>();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(false);

        XmlPullParser xmlPullParser = factory.newPullParser();
        xmlPullParser.setInput(new StringReader(xml));

        boolean insideItem = false;
        int eventType = xmlPullParser.getEventType();
        Article currentArticle = new Article();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name = xmlPullParser.getName();

            if (!TextUtils.isEmpty(name)) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (name.equalsIgnoreCase(RssKeywords.RSS_ITEM)) {
                        insideItem = true;
                    } else if (name.equalsIgnoreCase(RssKeywords.RSS_ITEM_TITLE)) {
                        if (insideItem) {
                            currentArticle.setTitle(xmlPullParser.nextText().trim());
                        }

                    } else if (name.equalsIgnoreCase(RssKeywords.RSS_ITEM_LINK)) {
                        if (insideItem) {
                            currentArticle.setLink(xmlPullParser.nextText().trim());
                        }

                    } else if (name.equalsIgnoreCase(RssKeywords.RSS_ITEM_AUTHOR)) {
                        if (insideItem) {
                            currentArticle.setAuthor(xmlPullParser.nextText().trim());
                        }

                    } else if (name.equalsIgnoreCase(RssKeywords.RSS_ITEM_CATEGORY)) {
                        if (insideItem) {
                            currentArticle.addCategory(xmlPullParser.nextText().trim());
                        }

                    } else if (name.equalsIgnoreCase(RssKeywords.RSS_ITEM_THUMBNAIL)) {
                        if (insideItem) {
                            currentArticle.setImage(xmlPullParser.getAttributeValue(null, RssKeywords.RSS_ITEM_URL));
                        }

                    } else if (name.equalsIgnoreCase(RssKeywords.RSS_ITEM_ENCLOSURE)) {
                        if (insideItem) {
                            String type = xmlPullParser.getAttributeValue(null, RssKeywords.RSS_ITEM_TYPE);

                            if (type != null && type.contains("image/")) {
                                currentArticle.setImage(xmlPullParser.getAttributeValue(null, RssKeywords.RSS_ITEM_URL));
                            }
                        }

                    } else if (name.equalsIgnoreCase(RssKeywords.RSS_ITEM_DESCRIPTION)) {
                        if (insideItem) {
                            String description = xmlPullParser.nextText();
                            currentArticle.setDescription(description.trim());

                            if (TextUtils.isEmpty(currentArticle.getImage())) {
                                currentArticle.setImage(getImageUrl(description));
                            }
                        }

                    } else if (name.equalsIgnoreCase(RssKeywords.RSS_ITEM_CONTENT)) {
                        if (insideItem) {
                            String content = xmlPullParser.nextText().trim();
                            currentArticle.setContent(content);

                            if (TextUtils.isEmpty(currentArticle.getImage())) {
                                currentArticle.setImage(getImageUrl(content));
                            }
                        }

                    } else if (name.equalsIgnoreCase(RssKeywords.RSS_ITEM_PUB_DATE)) {
                        if (insideItem) {
                            int nextTokenType = xmlPullParser.next();

                            if (nextTokenType == XmlPullParser.TEXT) {
                                currentArticle.setPubDate(xmlPullParser.getText().trim());
                            }

                            continue;
                        }
                    } else if (name.equalsIgnoreCase(RssKeywords.RSS_ITEM_TIME)) {
                        if (insideItem) {
                            currentArticle.setPubDate(xmlPullParser.nextText());
                        }
                    } else if (name.equalsIgnoreCase(RssKeywords.RSS_ITEM_GUID)) {
                        if (insideItem) {
                            currentArticle.setGuid(xmlPullParser.nextText().trim());
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG && name.equalsIgnoreCase("item")) {
                    insideItem = false;
                    list.add(currentArticle);
                    currentArticle = new Article();
                }
            }

            eventType = xmlPullParser.next();
        }

        return list;
    }

    private static String getImageUrl(String input) {
        String url = null;
        Pattern patternImg = Pattern.compile("(<img .*?>)");
        Matcher matcherImg = patternImg.matcher(input);

        if (matcherImg.find()) {
            String imgTag = matcherImg.group(1);
            Pattern patternLink = Pattern.compile("src\\s*=\\s*\"(.+?)\"");
            Matcher matcherLink = patternLink.matcher(imgTag);

            if (matcherLink.find()) {
                url = matcherLink.group(1).trim();
            }
        }

        return url;
    }
}
