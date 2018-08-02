import random

def mergesort(arr, begin, end):
    if len(arr) == 0 or begin > end:
        return list()

    if begin == end:
        return [arr[end]]
    
    left = mergesort(arr, begin, begin + (end - begin)//2)
    right = mergesort(arr, begin + (end - begin)//2 + 1, end)

    return mergeArray(left, right)

def mergeArray(left, right):
    res = list()
    leftIter = 0
    rightIter = 0
    leftlen = len(left)
    rightlen = len(right)
    
    for _i in range(leftlen + rightlen):
        if leftIter < leftlen and rightIter < rightlen:
            if left[leftIter] < right[rightIter]:
                res.append(left[leftIter])
                leftIter += 1
            else:
                res.append(right[rightIter])
                rightIter += 1
        elif leftIter < leftlen:
            res.append(left[leftIter])
            leftIter += 1
        else:
            res.append(right[rightIter])
            rightIter += 1

    return res

if __name__ == "__main__":
    arr = [random.randint(0, 100) for _i in range(50)]
    print("Is sorted?", mergesort(arr, 0, len(arr)-1) == sorted(arr))
