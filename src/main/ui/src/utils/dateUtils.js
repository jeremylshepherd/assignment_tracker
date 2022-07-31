function leadingZero(string) {
  return string.length === 1 ? `0${string}` : string;
}

export function convertISO(datestring) {
  let date = new Date(datestring);
  let month = leadingZero((date.getMonth() + 1).toString());
  let day = leadingZero(date.getDate().toString());
  let hours = leadingZero(date.getHours().toString());
  let minutes = leadingZero(date.getMinutes().toString());
  return `${month}/${day}/${date.getFullYear()} ${hours}:${minutes}`;
}
