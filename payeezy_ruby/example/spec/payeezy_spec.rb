require 'spec_helper'

describe "Sample calls to Payeezy" do

  before :each do
    options = {}
    options[:url]       = 'https://api-cat.payeezy.com/v1/transactions'
    options[:tokenURL]  = 'https://api-cat.payeezy.com/v1/transactions/tokens'
    options[:apikey]    = 'Jc9qkh5wkkPxv1K3o807N9wz0qfcHGQE'
    options[:apisecret] = '698189fe9a74ce3627835e594586c8b77e7d1a9aaa129ace9e4123bc13a15747'
    options[:token]     = 'fdoa-a480ce8951daa73262734cf102641994c1e55e7cdf4c02b6'

    @payeezy = Payeezy::Transactions.new options
  end

  describe "#new" do
    it 'returns an instance of Payeezy' do
      @payeezy.should be_an_instance_of Payeezy::Transactions
    end
  end

  describe "Execute Token Based Transactions" do
    it 'Tokenize Transaction' do
      @primary_response = @payeezy.transactGetToken(:getToken,tokenize_tx_payload)
      @token_response = @primary_response['token']
      printf "token Value is = %s\n", @token_response['value']
    end
  end
  
  describe "Execute Primary Transactions" do
    it 'Authorize Transaction' do
      @primary_response = @payeezy.transact(:authorize,primary_tx_payload)
      @primary_response['transaction_status'].should == "approved"
    end

    it 'Purchase Transaction' do
      @primary_response = @payeezy.transact(:purchase,primary_tx_payload)
      @primary_response['transaction_status'].should == "approved"
    end
  end

  describe "Execute Secondary Transactions" do
    it 'Capture Transaction' do
      @primary_response = @payeezy.transact(:authorize,primary_tx_payload)
      @primary_response['transaction_status'].should == "approved"
      @secondary_response = @payeezy.transact(:capture,secondary_tx_payload(@primary_response))
      @secondary_response['transaction_status'].should == "approved"
    end

    it 'Void Transaction' do
      @primary_response = @payeezy.transact(:authorize,primary_tx_payload)
      @primary_response['transaction_status'].should == "approved"
      @secondary_response = @payeezy.transact(:void,secondary_tx_payload(@primary_response))
      @secondary_response['transaction_status'].should == "approved"
    end

    it 'Refund Transaction' do
      @primary_response = @payeezy.transact(:purchase,primary_tx_payload)
      @primary_response['transaction_status'].should == "approved"
      @secondary_response = @payeezy.transact(:refund,secondary_tx_payload(@primary_response))
      @secondary_response['transaction_status'].should == "approved"
    end

    it 'Split Shipment Transaction' do
      @primary_response = @payeezy.transact(:authorize,primary_tx_payload)
      @primary_response['transaction_status'].should == "approved"

      @split_amount = @primary_response['amount'].to_i/2
      @secondary_response = @payeezy.transact(:split,secondary_tx_payload_split(@primary_response,"01/99",@split_amount))
      @secondary_response['transaction_status'].should == "approved"

      @secondary_response = @payeezy.transact(:split,secondary_tx_payload_split(@primary_response,"02/02",@split_amount))
      @secondary_response['transaction_status'].should == "approved"
    end
  end

  describe "Execute TeleCheck_Primary Transactions" do
    it 'Purchase Transaction' do
      @primary_response = @payeezy.transact(:purchase,primary_telecheck_tx_payload)
      @primary_response['transaction_status'].should == "approved"
    end
  end

  describe "Execute TeleCheck_Secondary Transactions" do

    it 'Void Transaction' do
      @primary_response = @payeezy.transact(:authorize,primary_telecheck_tx_payload)
      @primary_response['transaction_status'].should == "approved"
      @secondary_response = @payeezy.transact(:void,secondary_telecheck_tx_payload(@primary_response))
      @secondary_response['transaction_status'].should == "approved"
    end

    it 'Tagged Void Transaction' do
      @primary_response = @payeezy.transact(:purchase,primary_telecheck_tx_payload)
      @primary_response['transaction_status'].should == "approved"
      @secondary_response = @payeezy.transact(:refund,secondary_telecheck_tx_payload(@primary_response))
      @secondary_response['transaction_status'].should == "approved"
    end

    it 'Tagged Refund' do
      @primary_response = @payeezy.transact(:purchase,primary_telecheck_tx_payload)
      @primary_response['transaction_status'].should == "approved"
      @secondary_response = @payeezy.transact(:refund,secondary_telecheck_tx_payload(@primary_response))
      @secondary_response['transaction_status'].should == "approved"
    end
  end

  describe "Execute ValueLink_Primary Transactions" do
    it 'Purchase Transaction' do
      @primary_response = @payeezy.transact(:purchase,primary_valuelink_tx_payload)
      @primary_response['transaction_status'].should == "approved"
    end
  end

  describe "Execute ValueLink_Secondary Transactions" do

    it 'Void Transaction' do
      @primary_response = @payeezy.transact(:authorize,primary_valuelink_tx_payload)
      @primary_response['transaction_status'].should == "approved"
      @secondary_response = @payeezy.transact(:void,secondary_valuelink_tx_payload(@primary_response))
      @secondary_response['transaction_status'].should == "approved"
    end

    it 'Refund' do
      @primary_response = @payeezy.transact(:purchase,primary_valuelink_tx_payload)
      @primary_response['transaction_status'].should == "approved"
      @secondary_response = @payeezy.transact(:refund,secondary_valuelink_tx_payload(@primary_response))
      @secondary_response['transaction_status'].should == "approved"
    end
  end
  
  def tokenize_tx_payload
    credit_card = {}
    payload = {}
    payload[:type] = 'FDToken'
    payload[:auth]='false'
    payload[:ta_token]='NOIW'
   

    credit_card[:type] = 'visa'
    credit_card[:cardholder_name] = 'John Smith'
    credit_card[:card_number] = '4788250000028291'
    credit_card[:exp_date] = '1030'
    credit_card[:cvv] = '123'
    payload[:credit_card] = credit_card

    payload
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