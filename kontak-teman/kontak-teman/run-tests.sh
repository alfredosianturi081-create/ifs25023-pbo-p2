#!/usr/bin/env bash
# Compile aplikasi lalu jalankan semua test case di folder test-cases/.
# Setiap baris di TC-0X.expected harus muncul (berurutan) di output program.
set -e
cd "$(dirname "$0")"

rm -rf output
javac -d output -sourcepath src src/App.java

pass=0; fail=0
for tc in test-cases/TC-*.tc; do
    name=$(basename "$tc" .tc)
    actual=$(java -cp output App < "$tc" 2>&1 || true)
    if printf '%s\n' "$actual" | awk -v expfile="test-cases/$name.expected" '
        BEGIN { n = 0; while ((getline line < expfile) > 0) want[++n] = line; i = 1 }
        { if (i <= n && index($0, want[i]) > 0) i++ }
        END { exit (i > n) ? 0 : 1 }'; then
        echo "[PASS] $name"; pass=$((pass+1))
    else
        echo "[FAIL] $name"; fail=$((fail+1))
    fi
done
echo "Hasil: $pass lulus, $fail gagal"
[ "$fail" -eq 0 ]
