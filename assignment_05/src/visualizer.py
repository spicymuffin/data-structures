import matplotlib.pyplot as plt
import testgen
import subprocess

dct = {}

sample_sum = 0
sample_squared_sum = 0
sample_n = 0

PATH = __file__.replace("visualizer.py", "")[:-1]

subprocess.run(["javac", "-cp", PATH, "test_3989.java"])

print("compilation done")


TESTS_PER_TABLE_PARTCNT = 3
TABLES = [269, 2081, 3989, 8297, 16879, 102229]

for table in TABLES:
    print(f"testing: {1} parts, {table} tablesize:")
    for j in range(1, TESTS_PER_TABLE_PARTCNT + 1):

        dct = {}

        sample_sum = 0
        sample_squared_sum = 0
        sample_n = 0

        testgen.generate_test_random_high(
            table, 1, 4000000, 1 + 9999999999999, 9999999999999 + table * j
        )

        subprocess.run(["java", "-cp", PATH, "test_3989"])

        with open(f"{PATH}/output.txt") as f:
            for i in range(4000000):
                hash = int(f.readline())
                if hash not in dct.keys():
                    dct[hash] = 1
                else:
                    dct[hash] += 1

        for key in dct.keys():
            sample_n += 1
            sample_sum += dct[key]
            sample_squared_sum += dct[key] * dct[key]

        sample_mean = sample_sum / sample_n
        sample_variance = (
            ((sample_n * sample_squared_sum) - (sample_sum**2))
            / (sample_n)
            / (sample_n - 1)
        )
        sample_stdev = sample_variance ** (0.5)

        print(f"sample mean:     {sample_mean}")
        print(f"sample stdev:    {sample_stdev}")
        print(f"vmr:             {sample_variance/sample_mean}")
        print(f"sz:              {table}")

        plt.plot(*zip(*sorted(dct.items())))
        plt.show()
