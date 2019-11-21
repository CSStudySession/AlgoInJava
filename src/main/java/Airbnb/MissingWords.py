
def find_missing_words(s, t):
    a = s.split(" ")
    b = t.split(" ")
    c = []

    i = 0

    for w in b:
        while i < len(a):
            if w == a[i]:
                break
            else:
                c.append(a[i])
            i += 1
        i += 1

    while i < len(a):
        c.append(a[i])
        i += 1

    return c


if __name__ == '__main__':
    s = "I like cheese"
    t = "like"
    print(find_missing_words(s, t))