require 'test/unit'
require 'payeezy'

class PayeezyTest < Test::Unit::TestCase

  URL = 'https://api-cert.payeezy.com/v1/transactions'
  APIKEY = 'y6pWAJNyJyjGv66IsVuWnklkKUPFbb0a'
  APISECRET = '86fbae7030253af3cd15faef2a1f4b67353e41fb6799f576b5093ae52901e6f7'
  TOKEN = 'fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6'


  def test_authorize
    puts '----------------------------execute authorize-----------------------------------------------'
    puts 'Request ...'
    puts primary_tx_payload
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_tx_payload)
    puts 'Response ...'
    puts response
    if assert_equal('approved', response['transaction_status'] )
      puts 'Authorize test succeeded'
    else
      puts 'Authorize test failed'
    end
  end

  def test_capture
    puts '----------------------------execute capture -----------------------------------------------'
    # first do an auth, followed by the capture
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      puts secondary_tx_payload(response)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:capture,secondary_tx_payload(response))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts 'capture test succeeded'
      else
        puts 'capture test failed'
      end
    else
      puts 'Initial authorize for capture failed'
    end
  end

  def test_purchase
    puts '----------------------------execute purchase-----------------------------------------------'
    puts 'Request ...'
    puts primary_tx_payload
    response = Payeezy::Transactions.new(init_options).transact(:purchase,primary_tx_payload)
    puts 'Response ...'
    puts response
    if assert_equal('approved', response['transaction_status'] )
      puts 'Purchase test succeeded'
    else
      puts 'Purchase test failed'
    end
  end

  def test_refund
    puts '----------------------------execute refund -----------------------------------------------'
    # first do a purchase, followed by the refund
    response = Payeezy::Transactions.new(init_options).transact(:purchase,primary_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      puts secondary_tx_payload(response)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:refund,secondary_tx_payload(response))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts 'refund test succeeded'
      else
        puts 'refund test failed'
      end
    else
      puts 'Initial purchase for refund failed'
    end
  end

  def test_void
    puts '----------------------------execute void -----------------------------------------------'
    # first do an auth, followed by the void
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      puts secondary_tx_payload(response)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:void,secondary_tx_payload(response))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts 'void test succeeded'
      else
        puts 'void test failed'
      end
    else
      puts 'Initial authorize for void failed'
    end
  end

  def test_split
    puts '----------------------------execute split -----------------------------------------------'
    # first do an auth, followed by the 2 split shipments
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      @split_amount = response['amount'].to_i/2
      puts secondary_tx_payload_split(response,"01/99",@split_amount)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:split,secondary_tx_payload_split(response,"01/99",@split_amount))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts '1st split test succeeded'

        puts secondary_tx_payload_split(response,"02/02",@split_amount)
        secondary_response = Payeezy::Transactions.new(init_options).transact(:split,secondary_tx_payload_split(response,"02/02",@split_amount))
        puts 'Response ...'
        puts secondary_response
          if assert_equal('approved', secondary_response['transaction_status'] )
            puts 'final split test succeeded'
          else
            puts 'final split test failed'
          end
      else
        puts '1st split test failed'
      end
    else
      puts 'Initial authorize for split failed'
    end
  end

  def test_telecheck_purchase
    puts '----------------------------execute telecheck purchase-----------------------------------------------'
    puts 'Request ...'
    puts primary_telecheck_tx_payload
    response = Payeezy::Transactions.new(init_options).transact(:purchase,primary_telecheck_tx_payload)
    puts 'Response ...'
    puts response
    if assert_equal('approved', response['transaction_status'] )
      puts 'Purchase test succeeded'
    else
      puts 'Purchase test failed'
    end
  end

  def test_telecheck_void
    puts '----------------------------execute telecheck void -----------------------------------------------'
    # first do an auth, followed by the void
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_telecheck_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      puts secondary_telecheck_tx_payload(response)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:void,secondary_telecheck_tx_payload(response))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts 'void test succeeded'
      else
        puts 'void test failed'
      end
    else
      puts 'Initial authorize for void failed'
    end
  end

  def test_telecheck_tagged_void
    puts '----------------------------execute telecheck tagged void -----------------------------------------------'
    # first do an auth, followed by the void
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_telecheck_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      puts secondary_telecheck_tx_payload(response)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:void,secondary_telecheck_tx_payload(response))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts 'void test succeeded'
      else
        puts 'void test failed'
      end
    else
      puts 'Initial authorize for void failed'
    end
  end

  def test_telecheck_tagged_refund
    puts '----------------------------execute telecheck tagged refund -----------------------------------------------'
    # first do an auth, followed by the void
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_telecheck_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      puts secondary_telecheck_tx_payload(response)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:refund,secondary_telecheck_tx_payload(response))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts 'void test succeeded'
      else
        puts 'void test failed'
      end
    else
      puts 'Initial authorize for void failed'
    end
  end

  def test_valuelink_purchase
    puts '----------------------------execute valulink purchase-----------------------------------------------'
    puts 'Request ...'
    puts primary_telecheck_tx_payload
    response = Payeezy::Transactions.new(init_options).transact(:purchase,primary_telecheck_tx_payload)
    puts 'Response ...'
    puts response
    if assert_equal('approved', response['transaction_status'] )
      puts 'Purchase test succeeded'
    else
      puts 'Purchase test failed'
    end
  end

  def test_valuelink_void
    puts '----------------------------execute valuelink void -----------------------------------------------'
    # first do an auth, followed by the void
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_telecheck_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      puts secondary_telecheck_tx_payload(response)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:void,secondary_telecheck_tx_payload(response))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts 'void test succeeded'
      else
        puts 'void test failed'
      end
    else
      puts 'Initial authorize for void failed'
    end
  end

  def test_valuelink_activation
    puts '----------------------------execute valuelink activation -----------------------------------------------'
    # first do an auth, followed by the void
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_telecheck_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      puts secondary_telecheck_tx_payload(response)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:activation,secondary_telecheck_tx_payload(response))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts 'void test succeeded'
      else
        puts 'void test failed'
      end
    else
      puts 'Initial authorize for void failed'
    end
  end

  def test_valuelink_refund
    puts '----------------------------execute valuelink refund -----------------------------------------------'
    # first do an auth, followed by the void
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_telecheck_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      puts secondary_telecheck_tx_payload(response)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:refund,secondary_telecheck_tx_payload(response))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts 'void test succeeded'
      else
        puts 'void test failed'
      end
    else
      puts 'Initial authorize for void failed'
    end
  end

  def test_valuelink_cashout
    puts '----------------------------execute valuelink cashout -----------------------------------------------'
    # first do an auth, followed by the void
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_telecheck_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      puts secondary_telecheck_tx_payload(response)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:cashout,secondary_telecheck_tx_payload(response))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts 'void test succeeded'
      else
        puts 'void test failed'
      end
    else
      puts 'Initial authorize for void failed'
    end
  end

  def test_valuelink_partial_purchase
    puts '----------------------------execute valulink partial purchase -----------------------------------------------'
    puts 'Request ...'
    puts primary_telecheck_tx_payload
    response = Payeezy::Transactions.new(init_options).transact(:purchase,primary_telecheck_tx_payload)
    puts 'Response ...'
    puts response
    if assert_equal('approved', response['transaction_status'] )
      puts 'Purchase test succeeded'
    else
      puts 'Purchase test failed'
    end
  end

  def test_valuelink_deactivation
    puts '----------------------------execute valuelink deactivation -----------------------------------------------'
    # first do an auth, followed by the void
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_telecheck_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      puts secondary_telecheck_tx_payload(response)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:deactivation,secondary_telecheck_tx_payload(response))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts 'void test succeeded'
      else
        puts 'void test failed'
      end
    else
      puts 'Initial authorize for void failed'
    end
  end

  def test_valuelink_balance_inqury
    puts '----------------------------execute valuelink balance_inqury -----------------------------------------------'
    # first do an auth, followed by the void
    response = Payeezy::Transactions.new(init_options).transact(:authorize,primary_telecheck_tx_payload)
    if assert_equal('approved', response['transaction_status'] )
      puts 'Request ...'
      puts secondary_telecheck_tx_payload(response)
      secondary_response = Payeezy::Transactions.new(init_options).transact(:balance_inqury,secondary_telecheck_tx_payload(response))
      puts 'Response ...'
      puts secondary_response
      if assert_equal('approved', secondary_response['transaction_status'] )
        puts 'void test succeeded'
      else
        puts 'void test failed'
      end
    else
      puts 'Initial authorize for void failed'
    end
  end

  private

  def init_options
    options = {}
    options[:url] = URL
    options[:apikey] = APIKEY
    options[:apisecret] = APISECRET
    options[:token] = TOKEN

    options
  end

  def primary_tx_payload
    credit_card = {}
    payload = {}
    payload[:merchant_ref] = 'Astonishing-Sale'
    payload[:amount]='1200'
    payload[:currency_code]='USD'
    payload[:method]='credit_card'

    credit_card[:type] = 'visa'
    credit_card[:cardholder_name] = 'John Smith'
    credit_card[:card_number] = '4788250000028291'
    credit_card[:exp_date] = '1020'
    credit_card[:cvv] = '123'
    payload[:credit_card] = credit_card

    payload
  end

  def secondary_tx_payload(response)
    payload = {}
    payload[:merchant_ref] = 'Astonishing-Sale'
    payload[:transaction_tag] = response['transaction_tag']
    payload[:method]=response['method']
    payload[:amount]=response['amount']
    payload[:currency_code]=response['currency']
    payload[:transaction_id] = response['transaction_id']

    payload
  end

  def secondary_tx_payload_split(response, split_shipment, split_amount)
    payload = {}
    payload[:merchant_ref] = 'Astonishing-Sale'
    payload[:transaction_tag] = response['transaction_tag']
    payload[:method]=response['method']
    payload[:amount]=split_amount
    payload[:split_shipment]=split_shipment
    payload[:currency_code]=response['currency']
    payload[:transaction_id] = response['transaction_id']

    payload
  end

  def primary_telecheck_tx_payload
    tele_check = {}
    billing_address = {}
    payload = {}
    payload[:method] = 'tele_check'
    payload[:transaction_type] = 'purchase'
    payload[:amount] =  '10'
    payload[:currency_code] = 'USD'

    tele_check[:check_number] = '4788250000028291'
    tele_check[:check_type] = 'C'
    tele_check[:routing_number] = '123456789'
    tele_check[:account_number] = '123'
    tele_check[:accountholder_name] = 'Tom Eck'
    tele_check[:customer_id_type] = '1'
    tele_check[:customer_id_number] = '7623786df'
    tele_check[:client_email] = 'rajan.veeramani@firstdata.com'

    tele_check[:gift_card_amount] = '100'
    tele_check[:vip] = 'n'
    tele_check[:clerk_id] = 'RVK_001'
    tele_check[:device_id] = 'jkhsdfjkhsk'
    tele_check[:release_type] = 'X'
    tele_check[:registration_number] = '12345'
    tele_check[:registration_date] = '01012014'
    tele_check[:date_of_birth] = '01012010'
    payload[:tele_check] = tele_check

    billing_address[:street] = '225 Liberty Street'
    billing_address[:city] = 'NYC'
    billing_address[:state_province] = 'NY'
    billing_address[:zip_postal_code] = '10281'
    billing_address[:country] = 'US'
    payload[:billing_address] = billing_address

    payload

  end

  def secondary_telecheck_tx_payload(response)
    billing_address = {}
    payload = {}
    payload[:method] = 'tele_check'
    payload[:transaction_type] =response['transaction_type']
    payload[:amount]= '10'
    payload[:currency_code]=response['currency']

    billing_address[:street] = '225 Liberty Street'
    billing_address[:city] = 'NYC'
    billing_address[:state_province] = 'NY'
    billing_address[:zip_postal_code] = '10281'
    billing_address[:country] = 'US'
    payload[:billing_address] = billing_address

    payload[:transaction_tag] = response['transaction_tag']

    payload
  end

  def primary_valuelink_tx_payload

    payload = {}
    valuelink = {}

    payload[:amount] = '400'
    payload[:transaction_type] = 'purchase'
    payload[:method] = 'valuelink'
    payload[:currency_code] = 'USD'

    valuelink[:cardholder_name] = 'Joe Smith'
    valuelink[:cc_number] = '7777045839985463'
    valuelink[:credit_card_type] = 'Gift'

    payload[:valuelink] = valuelink

    payload

  end

  def secondary_valuelink_tx_payload(response)
    payload = {}
    valuelink = {}

    payload[:amount] = '400'
    payload[:transaction_type] = response['transaction_type']
    payload[:method] = 'valuelink'
    payload[:currency_code] = 'USD'

    valuelink[:cardholder_name] = 'Joe Smith'
    valuelink[:cc_number] = response['cc_number']
    valuelink[:credit_card_type] = 'Gift'

    payload[:valuelink] = valuelink

    payload
  end
end