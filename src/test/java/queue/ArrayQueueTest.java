package queue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayQueueTest {

    ArrayQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new ArrayQueue<>(5);
    }

    private void init() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
    }

    @Test
    void enqueue() {
        queue.enqueue(1);
        Assertions.assertThat(queue.front()).isEqualTo(1);
        Assertions.assertThat(queue.rear()).isEqualTo(1);
        queue.enqueue(2);
        Assertions.assertThat(queue.front()).isEqualTo(1);
        Assertions.assertThat(queue.rear()).isEqualTo(2);
        queue.enqueue(3);
        Assertions.assertThat(queue.front()).isEqualTo(1);
        Assertions.assertThat(queue.rear()).isEqualTo(3);
        queue.enqueue(4);
        Assertions.assertThat(queue.front()).isEqualTo(1);
        Assertions.assertThat(queue.rear()).isEqualTo(4);
        queue.enqueue(5);
        Assertions.assertThat(queue.front()).isEqualTo(1);
        Assertions.assertThat(queue.rear()).isEqualTo(5);

        Assertions.assertThatThrownBy(() -> queue.enqueue(6)).isInstanceOf(QueueFullException.class);
    }

    @Test
    void dequeue() {
        init();

        Assertions.assertThat(queue.dequeue()).isEqualTo(1);
        Assertions.assertThat(queue.front()).isEqualTo(2);
        Assertions.assertThat(queue.rear()).isEqualTo(5);

        Assertions.assertThat(queue.dequeue()).isEqualTo(2);
        Assertions.assertThat(queue.front()).isEqualTo(3);
        Assertions.assertThat(queue.rear()).isEqualTo(5);

        Assertions.assertThat(queue.dequeue()).isEqualTo(3);
        Assertions.assertThat(queue.front()).isEqualTo(4);
        Assertions.assertThat(queue.rear()).isEqualTo(5);

        Assertions.assertThat(queue.dequeue()).isEqualTo(4);
        Assertions.assertThat(queue.front()).isEqualTo(5);
        Assertions.assertThat(queue.rear()).isEqualTo(5);

        Assertions.assertThat(queue.dequeue()).isEqualTo(5);
        Assertions.assertThatThrownBy(() -> queue.front()).isInstanceOf(QueueEmptyException.class);
        Assertions.assertThatThrownBy(() -> queue.front()).isInstanceOf(QueueEmptyException.class);

        Assertions.assertThatThrownBy(() -> queue.dequeue()).isInstanceOf(QueueEmptyException.class);

        init();
        Assertions.assertThat(queue.front()).isEqualTo(1);
        Assertions.assertThat(queue.rear()).isEqualTo(5);
    }
}