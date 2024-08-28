// Post API 호출
function postApi(url, params, successCallback, errorCallback) {
  $.ajax({
    type: 'POST',
    url: url,
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    },
    data: JSON.stringify(params),
    success: function (data) {
      if (typeof successCallback === 'function') {
        successCallback(data);
      }
    },
    error: function (err) {
      if (typeof errorCallback === 'function') {
        errorCallback(err);
      }
    }
  });
}

// Get API 호출
function getApi(url, params, successCallback, errorCallback) {
  $.ajax({
    type: 'GET',
    url: url,
    headers: {
      'Accept': 'application/json'
    },
    data: params,
    success: function (data) {
      if (typeof successCallback === 'function') {
        successCallback(data);
      }
    },
    error: function (err) {
      if (typeof errorCallback === 'function') {
        errorCallback(err);
      }
    }
  });
}

// Put API 호출
function putApi(url, params, successCallback, errorCallback) {
  $.ajax({
    type: 'PUT',
    url: url,
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    },
    data: JSON.stringify(params),
    success: function (data) {
      if (typeof successCallback === 'function') {
        successCallback(data);
      }
    },
    error: function (err) {
      if (typeof errorCallback === 'function') {
        errorCallback(err);
      }
    }
  });
}

// Delete API 호출
function deleteApi(url, params, successCallback, errorCallback) {
  $.ajax({
    type: 'DELETE',
    url: url,
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    },
    data: JSON.stringify(params),
    success: function (data) {
      if (typeof successCallback === 'function') {
        successCallback(data);
      }
    },
    error: function (err) {
      if (typeof errorCallback === 'function') {
        errorCallback(err);
      }
    }
  });
}

function onApiError(err) {
  alert('에러');
  console.log(err);
}

function onApiSuccess(data){
  alert(data.msg);
  window.location.href = '/';
}