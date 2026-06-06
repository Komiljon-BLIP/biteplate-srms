package com.biteplate.biteplate_api.infrastructure.concurrency;

import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class ReservationQueue {

    private final BlockingQueue<ReservationTask> queue =
            new LinkedBlockingQueue<>();

    public void submit(
            ReservationTask task
    ) throws InterruptedException {

        queue.put(task);

    }

    public ReservationTask take()
            throws InterruptedException {

        return queue.take();

    }

    public int size() {

        return queue.size();

    }

}
