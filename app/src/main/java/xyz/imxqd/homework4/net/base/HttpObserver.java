package xyz.imxqd.homework4.net.base;



import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.imxqd.homework4.net.model.HttpResult;

public abstract class HttpObserver<T> implements Observer<HttpResult<T>> {

    @Override
    public void onNext(HttpResult<T> t) {
        if (t != null) {
            if (t.result != null && t.result.success) {
                onSuccess(t.result.data);
            } else if (t.result != null){
                onError(t.result.code, t.result.error);
            } else {
                onError(5002, "unknown error");
            }
        } else {
            onError(5001, "unknown error");
        }
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        onError(-1, e.getMessage());
    }

    public abstract void onSuccess(T data);
    public abstract void onError(int errorCode, String errorMessage);
}
