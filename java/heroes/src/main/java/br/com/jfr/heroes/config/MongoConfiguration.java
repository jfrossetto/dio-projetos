package br.com.jfr.heroes.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableReactiveMongoRepositories(value = "br.com.jfr.heroes.repository")
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.uri}")
    private String mongoUrl;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(mongoUrl);
    }

    /*
    @Override
    @Bean
    public MongoCustomConversions customConversions() {
        final List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new ZonedDateTimeToDocumentConverter());
        converters.add(new DocumentToZonedDateTimeConverter());
        converters.add(new MongoLocalDateTimeFromStringConverter());
        return new MongoCustomConversions(converters);
    }

    private static final class MongoLocalDateTimeFromStringConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(final String source) {
            return source == null ? null : LocalDateTime.parse(source);
        }
    }

    @WritingConverter
    public class ZonedDateTimeToDocumentConverter implements Converter<ZonedDateTime, Document> {
        static final String DATE_TIME = "dateTime";
        static final String ZONE = "zone";

        @Override
        public Document convert(@Nullable final ZonedDateTime zonedDateTime) {
            if (zonedDateTime == null) return null;

            final Document document = new Document();
            document.put(DATE_TIME, Date.from(zonedDateTime.toInstant()));
            document.put(ZONE, zonedDateTime.getZone().getId());
            document.put("offset", zonedDateTime.getOffset().toString());
            return document;
        }
    }

    @ReadingConverter
    public class DocumentToZonedDateTimeConverter implements Converter<Document, ZonedDateTime> {
        static final String DATE_TIME = "dateTime";
        static final String ZONE = "zone";

        @Override
        public ZonedDateTime convert(@Nullable final Document document) {
            if (document == null) return null;

            final Date dateTime = document.getDate(DATE_TIME);
            final String zoneId = document.getString(ZONE);
            final ZoneId zone = ZoneId.of(zoneId);

            return ZonedDateTime.ofInstant(dateTime.toInstant(), zone);
        }
    }
     */
}

