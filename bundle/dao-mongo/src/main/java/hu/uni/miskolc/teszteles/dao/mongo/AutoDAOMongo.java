package hu.uni.miskolc.teszteles.dao.mongo;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import hu.uni.miskolc.teszteles.core.Auto;
import hu.uni.miskolc.teszteles.core.exceptions.AjtokSzamaNemMegfelelo;
import hu.uni.miskolc.teszteles.core.exceptions.GyartasiIdoNemMegfelelo;
import hu.uni.miskolc.teszteles.core.exceptions.RendszamNemMegfelelo;
import hu.uni.miskolc.teszteles.dao.AutoDao;
import hu.uni.miskolc.teszteles.service.exception.AutoNemTalalhato;
import hu.uni.miskolc.teszteles.service.exception.RendszamMarFoglalt;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static com.mongodb.client.model.Filters.eq;

public class AutoDAOMongo implements AutoDao {
	private MongoCollection<AutoPojo> collection;
	
	public AutoDAOMongo(String url, String database, String collection) {
		ConnectionString connection = new ConnectionString(url);
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), 
				pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connection).
				codecRegistry(codecRegistry).build();
		
		MongoClient client = MongoClients.create(clientSettings);
		MongoDatabase db = client.getDatabase(database);
		this.collection = db.getCollection(collection, AutoPojo.class);
	}
	

	@Override
	public Collection<Auto> readAllAutos() {
		return collection.find().map(AutoPojoConverter::pojoToAutoConvert).into(new ArrayList());
		
	}

	@Override
	public Auto readAutoById(String rendszam) throws AutoNemTalalhato, RendszamNemMegfelelo {
	return collection.find(eq("_id",rendszam)).map(AutoPojoConverter::pojoToAutoConvert).first();
	}

	@Override
	public void createAuto(Auto auto) throws RendszamMarFoglalt {
		try {
		collection.insertOne(AutoPojoConverter.autoToPojoConvert(auto));}
		catch (MongoWriteException e) {
			throw new RendszamMarFoglalt(auto.getRendszam());
		}

	}

	@Override
	public void updateAuto(Auto auto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAuto(Auto auto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAutoById(String rendszam) {
		// TODO Auto-generated method stub

	}
	public void deleteAll(){
		collection.drop();
	}

}
