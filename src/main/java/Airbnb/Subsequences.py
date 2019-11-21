
def subsequence(prices):

    ret = 0

    for i in range(len(prices)):
        l = 0
        r = 0

        for j in range(0, i):
            if prices[j] > prices[i]:
                l += 1

        for k in range(i+1, len(prices)):
            if prices[i] > prices[k]:
                r += 1

        print("when i is %s, l is %s, r is %s" % (i, l, r))
        ret += l * r

    return ret


if __name__ == '__main__':
    prices = [5,3,4,2,1]
    print(subsequence(prices))