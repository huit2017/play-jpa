package providers.member;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import play.data.format.Formatters.AnnotationFormatter;
import play.data.format.Formatters;
import play.data.format.Formats.DateTime;
import play.data.format.Formatters.SimpleFormatter;
import play.i18n.Lang;
import play.i18n.MessagesApi;

@Singleton
public class FormattersProvider implements Provider<Formatters> {

    private final MessagesApi messagesApi;

    private final String pattern;

    private final String patternNoApp;

    public FormattersProvider(MessagesApi messagesApi) {
        this(messagesApi, "formats.date");
    }

    public FormattersProvider(MessagesApi messagesApi, String pattern) {
        this(messagesApi, pattern, "yyyy-MM-dd HH:mm:ss");
    }

    @Inject
    public FormattersProvider(MessagesApi messagesApi, String pattern, String patternNoApp) {
        this.messagesApi = messagesApi;
        this.pattern = pattern;
        this.patternNoApp = patternNoApp;
    }

    @Override
    public Formatters get() {
        Formatters formatters = new Formatters(messagesApi);

        /**
         * LocalDateTime
         */
        formatters.register(LocalDateTime.class, new SimpleFormatter<LocalDateTime>() {

            @Override
            public LocalDateTime parse(String text, Locale locale) throws ParseException {
                if (text == null || text.trim().isEmpty()) {
                    return null;
                }
                Lang lang = new Lang(locale);
                return LocalDateTime.parse(
                    text,
                    DateTimeFormatter.ofPattern(
                        Optional
                            .ofNullable(FormattersProvider.this.messagesApi)
                            .map(messages -> messages.get(lang, pattern))
                            .orElse(patternNoApp),
                        locale)
                 );
            }

            @Override
            public String print(LocalDateTime localDateTime, Locale locale) {
                Lang lang = new Lang(locale);
                return localDateTime.format(
                    DateTimeFormatter.ofPattern(
                        Optional
                            .ofNullable(FormattersProvider.this.messagesApi)
                            .map(messages -> messages.get(lang, pattern))
                            .orElse(patternNoApp), 
                        locale)
                );
            }

        });
        
        /**
         * LocalDateTime - annotation
         */
        formatters.register(LocalDateTime.class, new AnnotationFormatter<DateTime,LocalDateTime>() {

            @Override
            public LocalDateTime parse(DateTime annotation, String text, Locale locale) throws ParseException {
                
                if (text == null || text.trim().isEmpty()) {
                    return null;
                }
                Lang lang = new Lang(locale);
                return LocalDateTime.parse(
                    text,
                    DateTimeFormatter.ofPattern(
                        Optional
                            .ofNullable(FormattersProvider.this.messagesApi)
                            .map(messages -> messages.get(lang, annotation.pattern()))
                            .orElse(annotation.pattern()),
                        locale)
                 );
            }

            @Override
            public String print(DateTime annotation, LocalDateTime localDateTime, Locale locale) {
                Lang lang = new Lang(locale);
                return localDateTime.format(
                    DateTimeFormatter.ofPattern(
                        Optional
                            .ofNullable(FormattersProvider.this.messagesApi)
                            .map(messages -> messages.get(lang, annotation.pattern()))
                            .orElse(annotation.pattern()), 
                        locale)
                );
            }
        });
        
        /**
         * LocalDate
         */
        formatters.register(LocalDate.class, new SimpleFormatter<LocalDate>() {

            @Override
            public LocalDate parse(String text, Locale locale) throws ParseException {
                if (text == null || text.trim().isEmpty()) {
                    return null;
                }
                Lang lang = new Lang(locale);
                return LocalDate.parse(
                    text,
                    DateTimeFormatter.ofPattern(
                        Optional
                            .ofNullable(FormattersProvider.this.messagesApi)
                            .map(messages -> messages.get(lang, pattern))
                            .orElse(patternNoApp),
                        locale)
                 );
            }

            @Override
            public String print(LocalDate localDate, Locale locale) {
                Lang lang = new Lang(locale);
                return localDate.format(
                    DateTimeFormatter.ofPattern(
                        Optional
                            .ofNullable(FormattersProvider.this.messagesApi)
                            .map(messages -> messages.get(lang, pattern))
                            .orElse(patternNoApp), 
                        locale)
                );
            }

        });
        
        /**
         * LocalDate - annotation
         */
        formatters.register(LocalDate.class, new AnnotationFormatter<DateTime,LocalDate>() {

            @Override
            public LocalDate parse(DateTime annotation, String text, Locale locale) throws ParseException {
                
                if (text == null || text.trim().isEmpty()) {
                    return null;
                }
                Lang lang = new Lang(locale);
                return LocalDate.parse(
                    text,
                    DateTimeFormatter.ofPattern(
                        Optional
                            .ofNullable(FormattersProvider.this.messagesApi)
                            .map(messages -> messages.get(lang, annotation.pattern()))
                            .orElse(annotation.pattern()),
                        locale)
                 );
            }

            @Override
            public String print(DateTime annotation, LocalDate localDate, Locale locale) {
                Lang lang = new Lang(locale);
                return localDate.format(
                    DateTimeFormatter.ofPattern(
                        Optional
                            .ofNullable(FormattersProvider.this.messagesApi)
                            .map(messages -> messages.get(lang, annotation.pattern()))
                            .orElse(annotation.pattern()), 
                        locale)
                );
            }
        });
        
        /**
         * LocalTime
         */
        formatters.register(LocalTime.class, new SimpleFormatter<LocalTime>() {

            @Override
            public LocalTime parse(String text, Locale locale) throws ParseException {
                if (text == null || text.trim().isEmpty()) {
                    return null;
                }
                Lang lang = new Lang(locale);
                return LocalTime.parse(
                    text,
                    DateTimeFormatter.ofPattern(
                        Optional
                            .ofNullable(FormattersProvider.this.messagesApi)
                            .map(messages -> messages.get(lang, pattern))
                            .orElse(patternNoApp),
                        locale)
                 );
            }

            @Override
            public String print(LocalTime localTIme, Locale locale) {
                Lang lang = new Lang(locale);
                return localTIme.format(
                    DateTimeFormatter.ofPattern(
                        Optional
                            .ofNullable(FormattersProvider.this.messagesApi)
                            .map(messages -> messages.get(lang, pattern))
                            .orElse(patternNoApp), 
                        locale)
                );
            }

        });
        
        /**
         * LocalTime - annotation
         */
        formatters.register(LocalTime.class, new AnnotationFormatter<DateTime,LocalTime>() {

            @Override
            public LocalTime parse(DateTime annotation, String text, Locale locale) throws ParseException {
                
                if (text == null || text.trim().isEmpty()) {
                    return null;
                }
                Lang lang = new Lang(locale);
                return LocalTime.parse(
                    text,
                    DateTimeFormatter.ofPattern(
                        Optional
                            .ofNullable(FormattersProvider.this.messagesApi)
                            .map(messages -> messages.get(lang, annotation.pattern()))
                            .orElse(annotation.pattern()),
                        locale)
                 );
            }

            @Override
            public String print(DateTime annotation, LocalTime localTime, Locale locale) {
                Lang lang = new Lang(locale);
                return localTime.format(
                    DateTimeFormatter.ofPattern(
                        Optional
                            .ofNullable(FormattersProvider.this.messagesApi)
                            .map(messages -> messages.get(lang, annotation.pattern()))
                            .orElse(annotation.pattern()), 
                        locale)
                );
            }
        });
        return formatters;
    }
}