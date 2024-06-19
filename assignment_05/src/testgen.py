import random


def generate_test_random_high(TABLE_LEN, PART_CNT, OP_CNT, KEY_LOW, KEY_HIGH):
    with open(f"{__file__.replace('testgen.py', '')}input.txt", "w+") as f:
        f.write(f"{TABLE_LEN}\n")
        f.write(f"{PART_CNT}\n")

        f.write(f"{OP_CNT}\n")

        for i in range(OP_CNT):
            operation = "t"
            f.write(f"{operation} {random.randint(KEY_LOW, KEY_HIGH)}\n")


def generate_test_sequential(TABLE_LEN, PART_CNT, KEY_LOW, KEY_HIGH):
    with open(f"{__file__.replace('testgen.py', '')}input.txt", "w+") as f:
        f.write(f"{TABLE_LEN}\n")
        f.write(f"{PART_CNT}\n")

        OP_CNT = KEY_HIGH - KEY_LOW + 1
        f.write(f"{OP_CNT}\n")

        for i in range(KEY_LOW, KEY_HIGH + 1):
            operation = "t"
            f.write(f"{operation} {i}\n")


if __name__ == "__main__":
    generate_test_random_high(2, 3989, 4000000, 1000000000 - 3989 * 2, 1000000000)
