const items = [
  {
    id: 1,
    subjectId: 1,
    text: 'Counterfeit Money',
    code: 'M01'
  },
  {
    id: 2,
    subjectId: 1,
    text: 'Error Resulted to House sustained Monetary lost ',
    code: 'M02'
  },
  {
    id: 3,
    subjectId: 1,
    text: 'Fighting inside Casino / C01 ',
    code: 'M03'
  },
  {
    id: 4,
    subjectId: 1,
    text: 'Gambling while on Duty / J03',
    code: 'M04'
  },
  {
    id: 5,
    subjectId: 1,
    text: 'Losing bet not Cleared ',
    code: 'M05'
  },
  {
    id: 6,
    subjectId: 1,
    text: 'Losing bet Paid ',
    code: 'M06'
  },
  {
    id: 7,
    subjectId: 1,
    text: 'Over Changing ',
    code: 'M07'
  },
  {
    id: 8,
    subjectId: 1,
    text: 'Over Payment ',
    code: 'M08'
  },
  {
    id: 9,
    subjectId: 1,
    text: 'Push bet Paid ',
    code: 'M09'
  },
  {
    id: 10,
    subjectId: 1,
    text: 'Suspicious Act ',
    code: 'M10'
  },
  {
    id: 11,
    subjectId: 1,
    text: 'Theft ',
    code: 'M11'
  },
  {
    id: 12,
    subjectId: 1,
    text: 'Opportunist ',
    code: 'M12'
  }
]
export default [
  {
    url: '/titleListItem/save',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/titleListItem/delete/[A-Za-z0-9]',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/titleListItem/page',
    type: 'get',
    response: config => {
      return {
        code: 0,
        data: {
          "records": items ,
          "total": items.length,
          "size": 10,
          "current": 1,
          "searchCount": true,
          "pages": 1
        }
      }
    }
  },
  {
    url: '/titleListItem/list',
    type: 'get',
    response: _ => {
      return {
        code: 0,
        data: items

      }
    }
  }
]
