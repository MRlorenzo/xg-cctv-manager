import request from '@/utils/request'

export function saveCustomer( data ) {
  return request({
    url: '/customer/save',
    method: 'post',
    data
  })
}

export function deleteCustomerById( id ) {
  return request({
    url: '/customer/delete/'+id,
    method: 'post'
  })
}

export function getCustomersForPage( data ) {
  return request({
    url: '/customer/page',
    method: 'get',
    params: data
  })
}

export function updateCustomer( data ) {
  return request({
    url: '/customer/save',
    method: 'post',
    data
  })
}
