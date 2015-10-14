
echo  "------------------- payeezy auth request :start ------------------------------------\n\n"
curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @auth.txt  https://api-cert.payeezy.com/v1/transactions -v

echo "\n-------------------- payeezy auth request :end-----------------------------------"
echo "\n-------------------- payeezy purchase request :start ----------------------------"

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @purchase.txt  https://api-cert.payeezy.com/v1/transactions -v

echo "\n-------------------- payeezy purchase reqeust :end -----------------------------"
