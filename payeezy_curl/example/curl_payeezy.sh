


echo  "Credit Card Transactions..\n\n"

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @auth.txt  https://api-cert.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @purchase.txt  https://api-cert.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @capture.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @void.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @refund.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

echo  " eCheck or TeleCheck  Transactions..\n\n"

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./echeck/eCheckPurchase.txt  https://api-cert.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./echeck/eCheckVoid.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./echeck/eCheckTaggedVoid.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./echeck/eCheckTaggedRefund.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v


echo  " GiftCard or ValueLink  Transactions..\n\n"

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./gift/GiftCardPurchase.txt  https://api-cert.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./gift/GiftCardActivation.txt  https://api-cert.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./gift/GiftCardReload.txt  https://api-cert.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./gift/GiftCardCashout.txt  https://api-cert.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./gift/GiftCardVoid.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./gift/GiftCardRefund.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./gift/GiftCardTaggedVoid.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./gift/GiftCardTaggedRefund.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./gift/GiftCardDeActivation.txt  https://api-cert.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./gift/GiftCardPartialPurchase.txt  https://api-cert.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./gift/GiftCardBalanceInquiry.txt  https://api-cert.payeezy.com/v1/transactions -v

echo  " Tokenization Transactions..\n"
echo  " FDTOKEN Genreate - 0$ Auth True..\n\n"

curl -k -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./token/FDTokenGenerateAuthTrue.txt  https://api-cert.payeezy.com/v1/transactions/tokens -v

echo  " FDTOKEN Genreate - 0$ Auth False..\n\n"

curl -k -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./token/FDTokenGenerateAuthFalse.txt  https://api-cert.payeezy.com/v1/transactions/tokens -v

echo  " FDTOKEN Authorize & Purchase..\n\n"

curl -k -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./token/FDTokenAuthorize.txt  https://api-cert.payeezy.com/v1/transactions -v

curl -k -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./token/FDTokenPurchase.txt  https://api-cert.payeezy.com/v1/transactions -v

curl -k -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./token/FDTokenPurchaseMasterCard.txt  https://api-cert.payeezy.com/v1/transactions -v

echo  " FDTOKEN Capture, Void & Refund..\n\n"

curl -k -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./token/FDTokenCapture.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

curl -k -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./token/FDTokenVoid.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

curl -k -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./token/FDTokenRefund.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

curl -k -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./token/FDTokenRefundMasterCard.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

echo  " Purchase AVS German Direct Debit  Transactions..\n\n"

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./germandirectdebit/PurchaseAVSGermanDirectDebit.txt  https://api-cert.payeezy.com/v1/transactions -v


echo  " Credit AVS German Direct Debit  Transactions..\n\n"

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./germandirectdebit/CreditAVSGermanDirectDebit.txt  https://api-cert.payeezy.com/v1/transactions -v

echo  "Purchase Soft Desc German Direct Debit  Transactions..\n\n"

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./germandirectdebit/PurchaseSoftDescGermanDirectDebit.txt  https://api-cert.payeezy.com/v1/transactions -v


echo  " Credit Soft Desc German Direct Debit  Transactions..\n\n"

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./germandirectdebit/CreditSoftDescGermanDirectDebit.txt  https://api-cert.payeezy.com/v1/transactions -v

echo  " Purchase L2L3 German Direct Debit  Transactions..\n\n"

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./germandirectdebit/PurchaseL2L3GermanDirectDebit.txt  https://api-cert.payeezy.com/v1/transactions -v


echo  " Credit L2L3 German Direct Debit  Transactions..\n\n"

curl -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./germandirectdebit/CreditL2L3GermanDirectDebit.txt  https://api-cert.payeezy.com/v1/transactions -v


echo  " German Direct Debit Purchase-void Credit-Void for AVS, soft desc and L2L3 Transactions..\n\n"

curl -k -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./germandirectdebit/voidGermanDirectDebit.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v

echo  " German Direct Debit Purchase-refund Credit-refund for AVS, soft desc and L2L3 Transactions..\n\n"

curl -k -X POST -H "Content-Type:application/json" -H "apikey:y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a" -H "token:fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6" -H "Authorization:NDBkODhkMGQwZTEzYzkxNjc4OTc2MmM3ODFlZTA4M2MxNjcxZDY0ZDk5ZDFiOTdjZjkwNTFkNmY4NTUxNWRlYQ==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./germandirectdebit/refundGermanDirectDebit.txt  https://api-cert.payeezy.com/v1/transactions/{transaction_id} -v


echo  " \nTimeout Reversal..\n"
echo  " Use this method to submit timeout, reversal for credit and debit card payments. Supported transaction types are 'authorize' & 'purchase'...\n"
echo  " Note for US domiciled Merchants: This API is available only in production. Do not attempt this on Sandbox.\n\n"
echo  " ****** Please change reversal_id before you run the script ************* \n\n"


curl -X POST -H "Content-Type:application/json" -H "apikey:fP0iYUx4oJ8LolKl2LiOT1Zo94mL0IDQ" -H "token:fdoa-10b9547c3dfd3d5d3d9e01c726b952fb10b9547c3dfd3d5d" -H "Authorization:ZWJkMDIxOGQ0MzBiMTY5N2Q3MmQ0OTZkNzljMjY2OTQxOGZlZDA2ZjA0OWNmODdkMDgxNTMzMzdhYjBjNGZlNA==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./timeout_reversal/authorize.txt  https://api-qa.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:fP0iYUx4oJ8LolKl2LiOT1Zo94mL0IDQ" -H "token:fdoa-10b9547c3dfd3d5d3d9e01c726b952fb10b9547c3dfd3d5d" -H "Authorization:ZWJkMDIxOGQ0MzBiMTY5N2Q3MmQ0OTZkNzljMjY2OTQxOGZlZDA2ZjA0OWNmODdkMDgxNTMzMzdhYjBjNGZlNA==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./timeout_reversal/purchase.txt  https://api-qa.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:fP0iYUx4oJ8LolKl2LiOT1Zo94mL0IDQ" -H "token:fdoa-10b9547c3dfd3d5d3d9e01c726b952fb10b9547c3dfd3d5d" -H "Authorization:ZWJkMDIxOGQ0MzBiMTY5N2Q3MmQ0OTZkNzljMjY2OTQxOGZlZDA2ZjA0OWNmODdkMDgxNTMzMzdhYjBjNGZlNA==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./timeout_reversal/capture.txt  https://api-qa.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:fP0iYUx4oJ8LolKl2LiOT1Zo94mL0IDQ" -H "token:fdoa-10b9547c3dfd3d5d3d9e01c726b952fb10b9547c3dfd3d5d" -H "Authorization:ZWJkMDIxOGQ0MzBiMTY5N2Q3MmQ0OTZkNzljMjY2OTQxOGZlZDA2ZjA0OWNmODdkMDgxNTMzMzdhYjBjNGZlNA==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data @./timeout_reversal/refund.txt  https://api-qa.payeezy.com/v1/transactions -v

curl -X POST -H "Content-Type:application/json" -H "apikey:fP0iYUx4oJ8LolKl2LiOT1Zo94mL0IDQ" -H "token:fdoa-10b9547c3dfd3d5d3d9e01c726b952fb10b9547c3dfd3d5d" -H "Authorization:ZWJkMDIxOGQ0MzBiMTY5N2Q3MmQ0OTZkNzljMjY2OTQxOGZlZDA2ZjA0OWNmODdkMDgxNTMzMzdhYjBjNGZlNA==" -H "nonce:6770831660134717000" -H "timestamp:1423683480051" --data  @./timeout_reversal/reversal.txt  https://api-qa.payeezy.com/v1/transactions -v