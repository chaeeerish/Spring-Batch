```java
import java.util.*;

/*
 PriorityQueue
 
 add
 
 poll
 
 PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b)
*/

/*
 디스크 컨트롤러
 
 대기 큐: (작업의 번호, 작업의 요청 시각, 작업의 소요 시간)
 가장 우선순위가 높은 작업을 꺼내서 실행한다. (인터럽트 X)
 
 우선순위 
 1. 소요시간이 짧은 것 
 2. 요청시간이 빠른 것
 3. 작업의 번호가 작은 것

 [Input]
 jobs : 작업에 대한 정보 [요청 시점, 소요시간]
 
 [Output]
 return: 모든 요청의 반환 시간의 평균
 
 [Solution]
 0. 가장 먼저 도착하는 작업을 Queue에 넣는다. 
 1. Queue에서 작업을 뺀다. 
 2. 작업이 끝나는 시간 기준으로 도착했을 모든 작업들을 Queue에 넣는다. 
    2-1. 작업이 끝나는 시간 기준으로 아무 작업이 도착하지 않았을 경우, 가장 빠른 도착시간을 넣는다. 
 3. Queue가 빌때까지 반복한다. 

 [주의]

*/

class Solution {
    class Work {
        int number, register, execution;
        
        Work(int number, int register, int execution) {
            this.number = number; 
            this.register = register; 
            this.execution = execution;
        }
    }
    
    public int solution(int[][] jobs) {
        int time = 0;
        int 반환시간합 = 0;
        
        PriorityQueue<Work> waitingQueue = new PriorityQueue<>((a, b) -> {
            if (a.execution != b.execution) {
                return a.execution - b.execution;
            }
            
            if (a.register != b.register) {
                return a.register - b.register;
            }
            
            return a.number - b.number;
        });
        
        ArrayList<Work> workList = new ArrayList<>();
        
        for (int index = 0; index < jobs.length; index++) {
            workList.add(new Work(index, jobs[index][0], jobs[index][1]));
        }
        
        Collections.sort(workList, (a, b) -> a.register - b.register);
        
        Work firstWork = workList.remove(0);
        waitingQueue.add(firstWork);
        // ‼️ 첫번째 Work 등록시간으로 time을 초기화 해야한다. 0이 아닐 수 있기에..
        time = firstWork.register; 
        
        // ‼️ 동시에 시작하는 다른 Work가 있을 수 있다. 
        while (! workList.isEmpty() && workList.get(0).register <= time) {
            firstWork = workList.remove(0);
            waitingQueue.add(firstWork);
        }  
        
        while (! waitingQueue.isEmpty()) {
            Work nowWork = waitingQueue.poll(); 
            time += nowWork.execution;
            
            반환시간합 += (time - nowWork.register);
            
            while (! workList.isEmpty() && workList.get(0).register <= time) {
                waitingQueue.add(workList.remove(0));
            }            
            
            if (waitingQueue.isEmpty() && ! workList.isEmpty()) {
                Work nextWork = workList.remove(0); 
                if (time < nextWork.register) {
                    time = nextWork.register; 
                }
                waitingQueue.add(nextWork);
            }
        }
        
        System.out.println(반환시간합);
        return (int) (반환시간합 / jobs.length);
    }
}
```