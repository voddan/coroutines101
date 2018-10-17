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
 *
 *   ???
 *   [clients.random.errors.ErrorAsync]
 *   [clients.random.errors.ErrorAsyncCatch]
 *
 *    - Cancellation??
 *
 *   [clients.random.cancellation.SimpleCancellation]
 *   [clients.random.cancellation.ScopedCancellation]
 *   [clients.random.cancellation.ErrorParallelLaunches]
 *   [clients.random.cancellation.CancelParallelLaunches]
 *
 * - Other usages:
 *
 *   - Channels
 *
 *   - Actors
 *
 *   - Select
 *
 *   - Synchronous Generators
 *
 *   - Coroutine web server
 * */
object INDEX