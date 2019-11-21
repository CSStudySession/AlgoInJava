package Dropbox.哦你赛;


/**
 * pthread_mutex_t lock;
 * pthread_cond_t cond;
 * queue<string> seeds;
 * unordered_set<string> visited;
 * int nwait = 0;
 * int nthreads = N;
 * crawl() {
 *         while(1) {
 *                 pthread_mutex_lock(&lock);
 *                 while(seeds.empty()){
 *                         nwait++:
 *                         if(nwait==N) {
 *                                 pthread_cond_signal(&cond);
 *                                 pthread_mutex_unlock(&lock);
 *                                 return;
 *                         }
 *                         pthread_cond_wait(&cond, &lock);
 *                         nwait—;
 *                 }
 *                 string url = seeds.front();
 *                 seeds.pop();
 *                 pthread_mutex_unlock(&lock);
 *                 vector<string> urls = geturls(url);
 *                 pthread_mutex_lock(&lock);
 *                 for(auto &a:urls) {
 *                         if(visited.count(a)) continue;
 *                          visited.insert(a);
 *                         seeds.push(a);
 *                 }
 *                 if(!seeds.empty()) pthread_cond_broadcast(&cond);
 *                 pthread_mutex_unlock(&lock);
 *         }
 * }
 */
public class WebCrawler {
}
