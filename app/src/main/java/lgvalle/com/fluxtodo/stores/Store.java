package lgvalle.com.fluxtodo.stores;

import lgvalle.com.fluxtodo.actions.Action;
import lgvalle.com.fluxtodo.dispatcher.Dispatcher;

public abstract class Store {

    private final Dispatcher dispatcher;

    Store(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    void emitStoreChange() {
        dispatcher.emitChange(changeEvent());
    }

    abstract StoreChangeEvent changeEvent();

    public abstract void onAction(Action action);

    public interface StoreChangeEvent {
    }
}
