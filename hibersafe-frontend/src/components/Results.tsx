
interface Props {
  link: string;
  index: number;
  side: string;
}

export default function Results(props: Props){
  return (
    <>
    {console.log(props)}
    <a href={props.link}>Resultado {props.side}.{props.index}</a>
    </>
  )
}