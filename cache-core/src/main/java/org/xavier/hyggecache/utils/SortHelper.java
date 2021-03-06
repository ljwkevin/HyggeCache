package org.xavier.hyggecache.utils;


import org.xavier.hyggecache.utils.bo.BaseSortItem;

import java.util.Collections;
import java.util.List;

/**
 * 描述信息：<br/>
 * 排序工具
 *
 * @author Xavier
 * @version 1.0
 * @date 2018.11.30
 * @since Jdk 1.8
 */
public class SortHelper<T extends BaseSortItem> {

    public int getIndexOfTopK(List<T> target, Integer k) {
        int result = -1;
        if (target != null && k != null) {
            if (target.size() >= k) {
                result = getIndexOfTopK(target, 0, target.size() - 1, k);
            }
        }
        return result;
    }

    private int getIndexOfTopK(List<T> target, int low, int high, Integer k) {
        if (low == high) {
            return low;
        }
        int partitionIndex = partitionLeftBigger(target, low, high);
        // 当前分隔区域的左半区元素个数
        int headPartSize = partitionIndex - low;
        if (headPartSize >= k) {
            //求隔断的左半区第 k 大
            return getIndexOfTopK(target, low, partitionIndex - 1, k);
        } else {
            //求隔断的右半区第 k-i 大
            return getIndexOfTopK(target, partitionIndex, high, k - headPartSize);
        }
    }

    /**
     * target[low]~target[high]，以 target[low] 为分隔基准，将集合分隔成：<br/>
     * 左侧不小于分隔基准<br/>
     * 右侧不大于分隔基准<br/>
     * 随后返回原始 target[low] 元素的当前索引
     *
     * @param target 待分隔的集合
     * @param low    分隔的左边界索引
     * @param high   分隔的右边界索引
     * @return 返回原始 target[low] 元素的当前索引
     */
    private int partitionLeftBigger(List<T> target, int low, int high) {
        int partitionCount = target.get(low).getCount();
        // 缓存原始 array[low] 元素的值
        T partitionTarget = target.get(low);
        // 缓存原始 array[low] 元素的索引值
        int rowPartitionCountIndex = low;
        // 将 array[low+1]~array[high] 分隔成左边不小于 隔断值，右边不大于隔断值
        low++;
        while (high > low) {
            // 从右往左寻找一个比隔断值大的元素的索引
            while (target.get(high).getCount() <= partitionCount && high > low) {
                high--;
            }
            // 从左往右寻找一个比隔断值小的元素的索引
            while (target.get(low).getCount() >= partitionCount && high > low) {
                low++;
            }
            // 索引值不同则进行交换
            if (low != high) {
                Collections.swap(target, low, high);
            }
        }
        // 将分隔基准元素插入某位置以满足 左边元素均不小于隔断值 右边元素均不大于隔断值
        target.remove(rowPartitionCountIndex);
        // rowPartitionCountIndex 一定会在 low 的左边，所以 rowPartitionCountIndex 对应的元素移除，其右侧所有元素序号 -1
        if (partitionTarget.getCount() > target.get(low - 1).getCount()) {
            target.add(low - 1, partitionTarget);
        } else {
            target.add(low, partitionTarget);
        }
        return low;
    }
}
