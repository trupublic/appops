package pyramid.infra.benchmarking;

import jsinterop.annotations.JsType;

@JsType(namespace = "Benchmark")
public class Benchmark {

	public Benchmark(String ref, long millis, String item){
		setItem(item);
		setMillis(millis);
		setTopic(ref); // intentional quick fix switch of item / ref 
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public long getMillis() {
		return millis;
	}

	public void setMillis(long millis) {
		this.millis = millis;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	

	@Override
	public String toString() {
		return topic + " - " + millis + " - " + item ;
	}
	
	private String topic;
	
	private long millis;
	
	private String item;

}
