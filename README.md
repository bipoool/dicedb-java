# DiceDB Java SDK

**DiceDB-Java SDK** is a lightweight, Blocking-Queue based client library for [DiceDB](https://github.com/DiceDb/dice). 
It features easy-to-use API and built-in connection pooling (currently under testing). 
Designed for simplicity and performance, this SDK allows you to interact with DiceDB using native Java constructs.


## 🔧 Installation
Add the following dependency to your `pom.xml` if you're using Maven:

```xml
<dependency>
    <groupId>com.dice</groupId>
    <artifactId>dicedb-java</artifactId>
    <version>1.0</version>
</dependency>
```

## 🚀 Quick Start
```java
public static void main(String[] args) throws DiceDbException, InterruptedException {

    DiceDbManager  dice = new DiceDbManager ("localhost", 7379);

    Response response = dice.fire("FLUSHDB", List.of());
    System.out.println("Response: " + response);

    Response setResp = dice.fire("SET", List.of("key", "value"));
    System.out.println("Response: " + setResp.getSETRes());

    Response getResp = dice.fire("GET", List.of("key"));
    System.out.println("Response: " + getResp.getGETRes().getValue());

    Response zAddResp1 = dice.fire("ZADD", List.of("users","30", "u1"));
    System.out.println("Response: " + zAddResp1.getZADDRes().getCount());

    Response zAddResp2 = dice.fire("ZADD", List.of("users","50", "u2"));
    System.out.println("Response: " + zAddResp2.getZADDRes().getCount());

    Response zRankResp = dice.fire("ZRANK", List.of("users",  "u2"));
    System.out.println("Response: " + zRankResp.getZRANKRes().getElement());

    BlockingQueue<Response> watchResp = dice.watch("GET.WATCH", List.of("key"));
    
    int count = 0;
    while (count<10) {
        Response resp = watchResp.take();
        System.out.println("Response: " + resp.getGETRes().getValue());
        count++;
    }
    dice.close();

}
```

## ✅ Supported Commands
| Command       | Supported |
| ------------- | --------- |
| DECR          | ✅         |
| DECRBY        | ✅         |
| DEL           | ✅         |
| ECHO          | ✅         |
| EXISTS        | ✅         |
| EXPIRE        | ✅         |
| EXPIREAT      | ✅         |
| EXPIRETIME    | ✅         |
| FLUSHDB       | ✅         |
| GET           | ✅         |
| GETDEL        | ✅         |
| GETEX         | ✅         |
| GETSET        | ✅         |
| GET.WATCH     | ✅         |
| HANDSHAKE     | ✅         |
| HGET          | ✅         |
| HGETALL       | ✅         |
| HGETALL.WATCH | ✅         |
| HGET.WATCH    | ✅         |
| HSET          | ✅         |
| INCR          | ✅         |
| INCRBY        | ✅         |
| KEYS          | ✅         |
| PING          | ✅         |
| SET           | ✅         |
| TTL           | ✅         |
| TYPE          | ✅         |
| UNWATCH       | ✅         |
| ZADD          | ✅         |
| ZCARD         | ✅         |
| ZCARD.WATCH   | ✅         |
| ZCOUNT        | ✅         |
| ZCOUNT.WATCH  | ✅         |
| ZPOPMAX       | ✅         |
| ZPOPMIN       | ✅         |
| ZRANGE        | ✅         |
| ZRANGE.WATCH  | ✅         |
| ZRANK         | ✅         |
| ZRANK.WATCH   | ✅         |
| ZREM          | ✅         |

## 📌 Notes
1. Connection pooling is under testing and not yet validated. 
2. It uses blocking queues to manage client-server communication. 
3. Contributions and feedback are welcome.