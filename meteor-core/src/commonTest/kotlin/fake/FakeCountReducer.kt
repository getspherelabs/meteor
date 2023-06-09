package fake

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.reducer.Reducer

object FakeCountReducer : Reducer<FakeCountState, FakeCountWish, FakeCountEffect> {

    override fun reduce(state: FakeCountState, wish: FakeCountWish): Change<FakeCountState, FakeCountEffect> {
        return when (wish) {
            FakeCountWish.Decrease -> {
                expect { state.copy(count = state.count - 1) }
            }
            FakeCountWish.Increase -> {
                expect { state.copy(count = state.count + 1) }
            }
            FakeCountWish.Reset -> {
                expect { state.copy(count = 0) }
            }
            FakeCountWish.ZeroValue -> {
                effect {
                    FakeCountEffect.Failure("The value is zero")
                }
            }
        }
    }
}
