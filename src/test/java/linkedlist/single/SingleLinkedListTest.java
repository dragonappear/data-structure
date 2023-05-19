package linkedlist.single;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

class SingleLinkedListTest {

    SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();
    Comparator<Integer> comparator = new Comparator<>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };


    @Test
    @DisplayName("맨 앞에 노드 추가")
    void addFirst() throws Exception {

        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(5 - i);
        }

        List<Integer> allNodeData = linkedList.getAllNodeData();

        Assertions.assertThat(allNodeData).hasSize(5);

        for (int i = 0; i < 5; i++) {
            Assertions.assertThat(allNodeData.get(i)).isEqualTo(i + 1);
        }
    }

    @Test
    @DisplayName("맨 앞에 노드 추가")
    void addNodeAtFirst() throws Exception {
        linkedList.addLast(1);
        linkedList.addNodeAtLast(new SingleLinkedList.Node<Integer>(2));
        linkedList.addNodeAtLast(new SingleLinkedList.Node<Integer>(3));
        linkedList.addNodeAtLast(new SingleLinkedList.Node<Integer>(4));
        linkedList.addNodeAtLast(new SingleLinkedList.Node<Integer>(5));

        List<Integer> allNodeData = linkedList.getAllNodeData();

        Assertions.assertThat(allNodeData).hasSize(5);

        for (int i = 0; i < 5; i++) {
            Assertions.assertThat(allNodeData.get(i)).isEqualTo(i + 1);
        }
    }

    @Test
    @DisplayName("맨 뒤에 노드 추가")
    void addLast() throws Exception {

        for (int i = 0; i < 5; i++) {
            linkedList.addLast(5 - i);
        }

        List<Integer> allNodeData = linkedList.getAllNodeData();

        Assertions.assertThat(allNodeData).hasSize(5);

        for (int i = 0; i < 5; i++) {
            Assertions.assertThat(allNodeData.get(i)).isEqualTo(5 - i);
        }
    }

    @Test
    @DisplayName("맨 뒤에 노드 추가")
    void addNodeAtLast() throws Exception {

        for (int i = 0; i < 5; i++) {
            linkedList.addNodeAtLast(new SingleLinkedList.Node<>(5 - i));
        }

        List<Integer> allNodeData = linkedList.getAllNodeData();

        Assertions.assertThat(allNodeData).hasSize(5);

        for (int i = 0; i < 5; i++) {
            Assertions.assertThat(allNodeData.get(i)).isEqualTo(5 - i);
        }
    }

    @Test
    @DisplayName("맨 앞 + 맨 뒤 혼합 노드 추가")
    void addWithFirstAndLast() throws Exception {

        linkedList.addLast(3);
        linkedList.addFirst(2);
        linkedList.addLast(4);
        linkedList.addFirst(1);
        linkedList.addLast(5);

        List<Integer> allNodeData = linkedList.getAllNodeData();

        Assertions.assertThat(allNodeData).hasSize(5);
        Assertions.assertThat(linkedList.getSize()).isEqualTo(5);

        for (int i = 0; i < 5; i++) {
            Assertions.assertThat(allNodeData.get(i)).isEqualTo(i + 1);
        }
    }

    @Test
    @DisplayName("맨 앞 노드 삭제")
    void removeFirst() throws Exception {
        linkedList.addFirst(1);

        linkedList.removeFirst();

        List<Integer> allNodeData = linkedList.getAllNodeData();

        Assertions.assertThat(linkedList.getSize()).isZero();
        Assertions.assertThat(allNodeData).isNull();
    }

    @Test
    @DisplayName("맨 뒤 노드 삭제")
    void removeLast() throws Exception {
        linkedList.addFirst(1);

        linkedList.removeLast();

        List<Integer> allNodeData = linkedList.getAllNodeData();

        Assertions.assertThat(linkedList.getSize()).isZero();
        Assertions.assertThat(allNodeData).isNull();
    }

    @Test
    @DisplayName("맨 앞 + 맨 뒤 혼합 노드 삭제")
    void removeWithFirstAndLast() throws Exception {

        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);

        linkedList.removeLast();
        linkedList.removeLast();
        linkedList.removeFirst();

        List<Integer> allNodeData = linkedList.getAllNodeData();

        Assertions.assertThat(linkedList.getSize()).isEqualTo(2);
        Assertions.assertThat(allNodeData).hasSize(2);
        Assertions.assertThat(allNodeData.get(0)).isEqualTo(2);
        Assertions.assertThat(allNodeData.get(1)).isEqualTo(3);
    }

    @Test
    @DisplayName("clear test")
    void clear() throws Exception {

        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);

        linkedList.clear();

        List<Integer> allNodeData = linkedList.getAllNodeData();
        Assertions.assertThat(linkedList.getSize()).isZero();

        Assertions.assertThat(allNodeData).isNull();
    }


    @Test
    @DisplayName("deduplicate test")
    void deduplicate() throws Exception {
        linkedList.addLast(1);
        linkedList.addLast(1);
        linkedList.addLast(1);
        linkedList.addLast(4);
        linkedList.addLast(2);
        linkedList.addLast(2);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(4);

        List<Integer> beforeDeduplicate = linkedList.getAllNodeData();
        Assertions.assertThat(beforeDeduplicate.stream().filter(i -> i == 1).count()).isEqualTo(3);
        Assertions.assertThat(beforeDeduplicate.stream().filter(i -> i == 2).count()).isEqualTo(3);
        Assertions.assertThat(beforeDeduplicate.stream().filter(i -> i == 3).count()).isEqualTo(5);
        Assertions.assertThat(beforeDeduplicate.stream().filter(i -> i == 4).count()).isEqualTo(2);
        Assertions.assertThat(linkedList.getSize()).isEqualTo(13);

        linkedList.deduplicate();

        List<Integer> afterDeduplicate = linkedList.getAllNodeData();
        Assertions.assertThat(afterDeduplicate.stream().filter(i -> i == 1).count()).isEqualTo(1);
        Assertions.assertThat(afterDeduplicate.stream().filter(i -> i == 2).count()).isEqualTo(1);
        Assertions.assertThat(afterDeduplicate.stream().filter(i -> i == 3).count()).isEqualTo(1);
        Assertions.assertThat(afterDeduplicate.stream().filter(i -> i == 4).count()).isEqualTo(1);
        Assertions.assertThat(linkedList.getSize()).isEqualTo(4);
    }


    @Test
    @DisplayName("search test")
    void search() throws Exception {
        linkedList.addLast(1);
        Assertions.assertThat(linkedList.search(1, comparator)).isNotNull();
        Assertions.assertThat(linkedList.search(2, comparator)).isNull();
    }

    @Test
    @DisplayName("purge test")
    void purge() throws Exception {
        linkedList.addLast(1);
        linkedList.addLast(1);
        linkedList.addLast(1);
        linkedList.addLast(4);
        linkedList.addLast(2);
        linkedList.addLast(2);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(4);

        Assertions.assertThat(linkedList.getAllNodeData().stream().filter(i -> i == 1).count()).isEqualTo(3);
        linkedList.purge(1, comparator);
        Assertions.assertThat(linkedList.getAllNodeData().stream().filter(i -> i == 1).count()).isEqualTo(1);

        Assertions.assertThat(linkedList.getAllNodeData().stream().filter(i -> i == 2).count()).isEqualTo(3);
        linkedList.purge(2, comparator);
        Assertions.assertThat(linkedList.getAllNodeData().stream().filter(i -> i == 2).count()).isEqualTo(1);

        Assertions.assertThat(linkedList.getAllNodeData().stream().filter(i -> i == 3).count()).isEqualTo(5);
        linkedList.purge(3, comparator);
        Assertions.assertThat(linkedList.getAllNodeData().stream().filter(i -> i == 3).count()).isEqualTo(1);

        Assertions.assertThat(linkedList.getAllNodeData().stream().filter(i -> i == 4).count()).isEqualTo(2);
        linkedList.purge(4, comparator);
        Assertions.assertThat(linkedList.getAllNodeData().stream().filter(i -> i == 4).count()).isEqualTo(1);

        Assertions.assertThat(linkedList.getSize()).isEqualTo(4);
    }

    @Test
    @DisplayName("뒤집은 연결 리스트 조회")
    void getAllNodeDataFromBack() {
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);

        SingleLinkedList<Integer> reverseLinkedList = linkedList.getReverseLinkedList();
        List<Integer> allNodeData = reverseLinkedList.getAllNodeData();
        for (int i = 0; i < 5; i++) {
            Assertions.assertThat(allNodeData.get(i)).isEqualTo(5 - i);
        }
        reverseLinkedList.print();
    }

    @Test
    @DisplayName("연결 리스트 합 구하기")
    void sumOfListTest() throws Exception {
        SingleLinkedList<Integer> ll1 = new SingleLinkedList<>();
        SingleLinkedList<Integer> ll2 = new SingleLinkedList<>();

        linkedList.addLast(8);
        linkedList.addLast(8);
        linkedList.addLast(8);

        ll1.addLast(9);
        ll1.addLast(9);
        ll1.addLast(9);
        ll1.addLast(9);

        SingleLinkedList<Integer> result1 = linkedList.sumOfIntegerLinkedList(ll1);
        result1.print();

        SingleLinkedList<Integer> result2 = linkedList.sumOfIntegerLinkedList(ll2);
        result2.print();
    }


    @Test
    @DisplayName("회문 X 테스트")
    void invalidPalindromeTest() throws Exception {
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        Assertions.assertThat(linkedList.isPalindromeLinkedList(Comparator.naturalOrder())).isFalse();
    }

    @Test
    @DisplayName("교집합 테스트")
    void intersectionTest() throws Exception {

        SingleLinkedList.Node<Integer> node = new SingleLinkedList.Node<>(7);
        linkedList.addNodeAtFirst(node);
        linkedList.addLast(2);
        linkedList.addLast(1);

        SingleLinkedList<Integer> list1 = new SingleLinkedList<>();
        SingleLinkedList<Integer> list2 = new SingleLinkedList<>();
        
        list1.addLast(3);
        list1.addLast(1);
        list1.addLast(5);
        list1.addLast(9);
        list2.addLast(4);
        list2.addLast(6);

        Assertions.assertThat(list1.getIntersectionNodeSet(list2)).isNull();

        list1.addNodeAtLast(node);
        list2.addNodeAtLast(node);
        Assertions.assertThat(list1.getIntersectionNodeSet(list2)).isEqualTo(node);
    }

    @Test
    @DisplayName("회문 O 테스트")
    void validPalindromeTest() throws Exception {
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(2);
        linkedList.addLast(1);
        Assertions.assertThat(linkedList.isPalindromeLinkedList(Comparator.naturalOrder())).isTrue();
    }

    @Test
    @DisplayName("getDataFromFront test")
    void getDataFromFrontTest() throws Exception {
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);

        Assertions.assertThat(linkedList.getAt(3)).isEqualTo(4);
        Assertions.assertThat(linkedList.getAt(-1)).isNull();
        Assertions.assertThat(linkedList.getAt(50)).isNull();
    }

    @Test
    @DisplayName("getDataFromBack test")
    void getDataFromBackTest() throws Exception {
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);

        Assertions.assertThat(linkedList.getAtFromBack(1)).isEqualTo(4);
        Assertions.assertThat(linkedList.getAtFromBack(2)).isEqualTo(3);
        Assertions.assertThat(linkedList.getAtFromBack(-1)).isNull();
        Assertions.assertThat(linkedList.getAtFromBack(50)).isNull();
    }

    @Test
    @DisplayName("getMiddleNodeData test")
    void getMiddleNodeDataTest() throws Exception {
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        Assertions.assertThat(linkedList.getMiddleNodeData()).isEqualTo(3);

        linkedList.addLast(6);
        Assertions.assertThat(linkedList.getMiddleNodeData()).isEqualTo(4);

        linkedList.addLast(7);
        Assertions.assertThat(linkedList.getMiddleNodeData()).isEqualTo(4);

    }
}