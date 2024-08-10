package t1.ismailov.timetracking.util;

import org.springframework.stereotype.Component;
import t1.ismailov.timetracking.dto.Statistics;
import t1.ismailov.timetracking.entity.ExecutionTime;

import java.util.List;
import java.util.LongSummaryStatistics;

@Component
public class CalculateStatistic {

    public Statistics calculate(List<ExecutionTime> execTimes) {
        if (execTimes.isEmpty()) {
            return new Statistics();
        }

        long min = execTimes.stream().mapToLong(ExecutionTime::getExecutionTime).min().orElse(0);
        long max = execTimes.stream().mapToLong(ExecutionTime::getExecutionTime).max().orElse(0);
        LongSummaryStatistics stats = execTimes.stream().mapToLong(ExecutionTime::getExecutionTime).summaryStatistics();
        long successfulCount = execTimes.stream().filter(ExecutionTime::isSuccessfully).count();
        double successfullyPercent = (double) successfulCount / stats.getCount() * 100;

        return new Statistics()
                .setMinExecutionTime(min)
                .setMaxExecutionTime(max)
                .setAverageExecutionTime(stats.getAverage())
                .setSuccessfullyPercent(successfullyPercent)
                .setCountWorks(stats.getCount());
    }
}
