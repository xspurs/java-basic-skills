package com.brctl.multithread.producerconsumer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static com.brctl.multithread.producerconsumer.WordCounter.FILE_PATH;

/**
 * 生产者/消费者word counter
 * TODO 对比下《七周》中的样例代码
 * {@link java.util.concurrent.LinkedBlockingQueue}
 * @author duanxiaoxing
 * @created 2017/8/13
 */
@Slf4j
public class WordCounter {

    public static final String FILE_PATH = "/Users/duanxiaoxing/Desktop/to_word_count.txt";
//    public static final String FILE_PATH = "/Users/duanxiaoxing/Desktop/gone_with_the_wind.txt";

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File(FILE_PATH);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BlockingQueue<Line> queue = new LinkedBlockingQueue<>(100);
        final ConcurrentHashMap<String, Integer> counts = new ConcurrentHashMap<>();
        Thread parser = new Thread(new Parser(FILE_PATH, queue));
        Thread counter = new Thread(new Counter(counts, queue));

        Long startTime = System.currentTimeMillis();
        /* single parser + single counter
        parser.start();
        counter.start();
        parser.join();
        queue.put(new PoisonPill());
        counter.join();
        */
        // more counter
        //
        ExecutorService executorService = Executors.newCachedThreadPool();
        int COUNTER_NUMBER = 4;
        for (int i = 0; i < COUNTER_NUMBER; i++) {
            executorService.execute(new Counter(counts, queue));
        }
        parser.start();
        parser.join();
        for (int i = 0; i < COUNTER_NUMBER; i++) {
            queue.put(new PoisonPill());
        }
        executorService.shutdown();
        log.info("total time(milliseconds): {}", System.currentTimeMillis() - startTime);

        log.info("statistics: {}", counts);

    }
}

@NoArgsConstructor
class Line {
    @Getter
    private String content;
    @Getter
    @Setter
    private boolean isPoisonPillLine = false;

    public Line(String content) {
        this.content = content;
    }
}

class PoisonPill extends Line {
    public PoisonPill() {
        this.setPoisonPillLine(true);
    }
}

@AllArgsConstructor
class Parser implements Runnable {
    private String filePath;
    private BlockingQueue<Line> queue;

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String content;
            while ((content = bufferedReader.readLine()) != null) {
                queue.put(new Line(content));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

@AllArgsConstructor
class Counter implements Runnable {
    private ConcurrentHashMap<String, Integer> counts;
    private BlockingQueue<Line> queue;
    private final String WORD_SEPARATOR = " ";
    private ConcurrentHashMap<String, Integer> localCounts;

    public Counter(ConcurrentHashMap<String, Integer> counts, BlockingQueue<Line> queue) {
        this.counts = counts;
        this.queue = queue;
        this.localCounts = new ConcurrentHashMap<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Line line = queue.take();
                if (line.isPoisonPillLine()) {  // find poison pill, complete
                    mergeCount();
                    break;
                }
                String[] words = line.getContent().split(WORD_SEPARATOR);
                for (String word : words) {
                    while (true) {
                        Integer nowCount;
                        if ((nowCount = localCounts.putIfAbsent(word, 1)) == null) {
                            break;
                        } else if (localCounts.replace(word, nowCount, nowCount + 1)) {
                            break;
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void mergeCount() {
        for (Map.Entry<String, Integer> entry : localCounts.entrySet()) {
            String word = entry.getKey();
            Integer count = entry.getValue();
            while (true) {
                Integer currentCount = counts.get(word);
                if (currentCount == null) {
                    if (counts.putIfAbsent(word, count) == null)  {
                        break;
                    } else if (counts.replace(word, currentCount, currentCount + count)) {
                        break;
                    }
                }
            }
        }
    }
}

// generate random english novel
@Slf4j
class PrepareEnglishNovel {
    private static final Random RANDOM = new Random();
    private static final char[] ENGLISH_CHARS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y','z'};
    private static final int LINE_COUNT = 500000;
    private static final int[] WORD_LENGTH = {3, 4, 5, 6, 7, 8};   // 随机单词长度
    private static final int[] LINE_WORD_COUNT = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};  // 每行单词数
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File(FILE_PATH)));
            for (int i = 0; i < LINE_COUNT; i++) {
                for (int j = 0; j < LINE_WORD_COUNT[RANDOM.nextInt(10)]; j++) {
                    for (int k = 0; k < WORD_LENGTH[RANDOM.nextInt(6)]; k++) {
                        stringBuilder.append(ENGLISH_CHARS[RANDOM.nextInt(24)]);
                    }
                    stringBuilder.append(" ");
                }
                stringBuilder.append("\n");
                bufferedWriter.append(stringBuilder);
                stringBuilder.delete(0, stringBuilder.length());
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            log.error("write to file error", e);
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }
}
