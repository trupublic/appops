package org.appops.infra.dispatcher;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

class ServiceMethodDeserializer implements JsonDeserializer<ServiceMethod> {

	Class<?>[] paramTypes = null;
	JsonElement element = null;
	JsonElement argumentJson = null;

	public ServiceMethodDeserializer() {

	}

	public Object[] getArguments(Class<?>[] types) {

		paramTypes = types; // Just in case we need this type array later on
							// from this deserializer

		if (types.length == 0)
			return null;

		Object[] arguments = new Object[types.length];
		
		if (argumentJson != null) {
			if (argumentJson.isJsonObject()) {
				JsonObject obj = argumentJson.getAsJsonObject();
				Gson gson = new Gson();
				gson.fromJson(obj, types[0]);
			} else if (argumentJson.isJsonArray()) {

				JsonArray array = argumentJson.getAsJsonArray();

				for (int i = 0; i < types.length; i++) {

					JsonElement arrayEle = array.get(i);

					Gson gson = new Gson();

					arguments[i] = gson.fromJson(arrayEle, types[i]);
				}
			}
		}
		return arguments;
	}

	@Override
	public ServiceMethod deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {

		element = json;
		argumentJson = json.getAsJsonObject().get("arguments");

		ServiceMethod method = new ServiceMethod();

		return method;
	}

	public String getServiceName() {
		return element.getAsJsonObject().get("service").getAsString();
	}
	
	public String getRef() {
		return element.getAsJsonObject().get("ref").getAsString();
	}

	public String getMethodName() {
		return element.getAsJsonObject().get("method").getAsString();
	}

}