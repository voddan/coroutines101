import clients.random.errors.ErrorSuspend

/**
 * - Why are we here?
 *
 *   - What are coroutines? [theory.SuspendableComputation]
 *
 * - Asynchronous clients:
 *
 *   - One Async Request
 *
 *   [clients.random.single.AsUsual]
 *   [clients.random.single.LetsSuspend]
 *   [clients.random.single.LaunchingACoroutine]
 *
 *   - Multiple Async Requests
 *
 *   [clients.random.several.SumOfTwo]
 *   [clients.random.several.RequestsInALoop]
 *
 *   - Parallel Async Requests
 *
 *   [clients.random.parallel.sumoftwo.ParallelSumOfTwo]
 *   [clients.random.parallel.loopsum.ParallelLoopSum]
 *
 *  - Keeping the house clean:
 *
 *    - Error Handling
 *
 *   [clients.random.errors.ErrorSuspend]
 *   [clients.random.errors.ErrorSuspendCatch]
 *   [clients.random.errors.ErrorLaunch]
 *   [clients.random.errors.ErrorLaunchCatch]
 *   [clients.random.errors.ErrorAsync]
 *   [clients.random.errors.ErrorAsyncCatch]
 *
 *    - Cancellation
 *
 * - Other usages:
 *
 *   - Channels
 *
 *   - Actors
 *
 *   - Coroutine web server
 * */
object INDEX